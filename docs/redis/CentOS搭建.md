CentOS安装

1.Virtual Box 创建虚拟机，分配内存1G，虚拟硬盘8G
2.CentOS-7-x86_64-Minimal-1810.iso 镜像，虚拟机安装界面，需要勾选网络连接
3.安装完成，网卡选择‘桥接网卡’
4.cd /etc/sysconfig/network-scripts 打开网络配置文件
5.安装jdk8，WinSCP 将安装文件jdk-8u191-linux-i586.rpm 拷贝到linux /usr/local文件夹下，安装命令rpm -ivh jdk-8u191-linux-i586.rpm
设置环境变量

[root@localhost opt]# vi /etc/profile                          ;编辑系统配置文件

然后输入i ,最后增加下面内容

export JAVA_HOME=/usr/local/java/jdk1.8.0_191
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin

最后按exc键，然后输入冒号，最后输入:wq 保存退出

source /etc/profile //重新载入

遇到问题：

-bash: /usr/bin/java: /lib/ld-linux.so.2: bad ELF interpreter: No such file or directory

解决办法：

yum install glibc.i686

压缩包方式：
登录Linux，切换到root用户
su root 获取root用户权限，当前工作目录不变(需要root密码)
或 sudo -i 不需要root密码直接切换成root（需要当前用户密码）
（1）在usr目录下建立java安装目录
	cd /usr
	mkdir java
（2）通过WinSCP 将jdk-8u191-linux-x64.tar.gz 拷贝到linux /usr/local文件夹下
（3）解压缩 tar -zxvf jdk-8u60-linux-x64.tar.gz 得到文件夹 jdk1.8.0_191
（4）安装完毕为他建立一个链接以节省目录长度
(我没用这一步)
	ln -s /usr/java/jdk1.8.0_60/ /usr/jdk
（5）编辑配置文件，配置环境变量
	vim /etc/profile
添加如下内容：JAVA_HOME根据实际目录来
JAVA_HOME=/usr/java/jdk1.8.0_60
CLASSPATH=$JAVA_HOME/lib/
PATH=$PATH:$JAVA_HOME/bin
export PATH JAVA_HOME CLASSPATH
（6）重启机器或执行命令 ：source /etc/profile
  sudo shutdown -r now
（7）查看安装情况
java -version
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) Client VM (build 25.191-b12, mixed mode)

可能出现的错误信息：
bash: ./java: cannot execute binary file

出现这个错误的原因可能是在32位的操作系统上安装了64位的jdk，
查看jdk版本和Linux版本位数是否一致。
查看你安装的Ubuntu是32位还是64位系统：
sudo uname --m
i686 //表示是32位
x86_64 // 表示是64位

6. 安装Redis
将下载好的压缩包放到/usr/local目录下
 tar xzf redis-5.0.3.tar.gz
 cd redis-5.0.3
 make
提示错误 make: cc: Command not found make: *** [adlist.o] Error 127
没有安装gcc环境，需要安装gcc
 yum install gcc
安装后检查是否安装成功
 rpm -qa |grep gcc
之后重新make

gcc编译redis时报错：

zmalloc.h:50:31: error: jemalloc/jemalloc.h: No such file or directory
zmalloc.h:55:2: error: #error "Newer version of jemalloc required"
原因是jemalloc重载了Linux下的ANSI C的malloc和free函数。解决办法：make时添加参数。

make MALLOC=libc

报错tcl缺失
yum install tcl

解决这些问题，继续安装redis 
make clean 一下，重新执行 make && make test && make install

只需要用到两个文件就可以了：redis-server和redis-cli

/usr/local/目录下创建了一个redis 目录

 cd /usr/local/

 mkdir redis

然后将src目录下的redis-server和server-cli 复制到redis目录下

[root@localhost src]# cp redis-cli redis-server /usr/local/redis/

redis.conf也拷贝到/usr/local/redis/目录下

修改redis.conf 文件，将daemonize no 改为daemonize yes

执行命令：  ./redis-server redis.conf  可以让Redis 后台运行
关闭所有redis进程命令，killall redis-server

pstree命令查看是否存在redis进程

linux环境需要安装 yum list | grep ** 也没有找到可安装的包. 
实际上在linux平台要安装psmisc

即可执行
pstree

systemd─┬─NetworkManager─┬─dhclient
        │                └─2*[{NetworkManager}]
        ├─auditd───{auditd}
        ├─chronyd
        ├─crond
        ├─dbus-daemon───{dbus-daemon}
        ├─firewalld───{firewalld}
        ├─login───bash
        ├─lvmetad
        ├─master─┬─pickup
        │        └─qmgr
        ├─polkitd───6*[{polkitd}]
        ├─redis-server───3*[{redis-server}]
        ├─rsyslogd───2*[{rsyslogd}]
        ├─sshd─┬─sshd───sftp-server
        │      └─sshd───bash───pstree
        ├─systemd-journal
        ├─systemd-logind
        ├─systemd-udevd
        └─tuned───4*[{tuned}]

killall redis-server