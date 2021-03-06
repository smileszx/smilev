# 1. 前言

`单例(Singleton)`应该是开发者们最熟悉的设计模式了，并且好像也是最容易实现的——基本上每个开发者都能够随手写出——但是，真的是这样吗？
 作为一个Java开发者，也许你觉得自己对单例模式的了解已经足够多了。我并不想危言耸听说一定还有你不知道的——毕竟我自己的了解也的确有限，但究竟你自己了解的程度到底怎样呢？往下看，我们一起来聊聊看~

# 2. 什么是单例？

`单例对象的类必须保证只有一个实例存在`——这是维基百科上对单例的定义，这也可以作为对意图实现单例模式的代码进行检验的标准。

对单例的实现可以分为两大类——`懒汉式`和`饿汉式`，他们的区别在于：
 `懒汉式`：指全局的单例实例在第一次被使用时构建。
 `饿汉式`：指全局的单例实例在类装载时构建。

从它们的区别也能看出来，日常我们使用的较多的应该是`懒汉式`的单例，毕竟按需加载才能做到资源的最大化利用嘛~

# 3. 懒汉式单例

先来看一下懒汉式单例的实现方式。

## 3.1 简单版本

看最简单的写法Version 1：

```
// Version 1
public class Single1 {
    private static Single1 instance;
    public static Single1 getInstance() {
        if (instance == null) {
            instance = new Single1();
        }
        return instance;
    }
}
```

或者再进一步，把构造器改为私有的，这样能够防止被外部的类调用。

```
// Version 1.1
public class Single1 {
    private static Single1 instance;
    private Single1() {}
    public static Single1 getInstance() {
        if (instance == null) {
            instance = new Single1();
        }
        return instance;
    }
}
```

我仿佛记得当初学校的教科书就是这么教的？—— 每次获取instance之前先进行判断，如果instance为空就new一个出来，否则就直接返回已存在的instance。
 这种写法在大多数的时候也是没问题的。**问题在于，当多线程工作的时候，如果有多个线程同时运行到if (instance == null)，都判断为null，那么两个线程就各自会创建一个实例——这样一来，就不是单例了**。

## 3.2 synchronized版本

那既然可能会因为多线程导致问题，那么加上一个同步锁吧！
 修改后的代码如下，相对于Version1.1，只是在方法签名上多加了一个`synchronized`：

```
// Version 2 
public class Single2 {
    private static Single2 instance;
    private Single2() {}
    public static synchronized Single2 getInstance() {
        if (instance == null) {
            instance = new Single2();
        }
        return instance;
    }
}
```

OK，加上`synchronized`关键字之后，getInstance方法就会锁上了。如果有两个线程（T1、T2）同时执行到这个方法时，会有其中一个线程T1获得同步锁，得以继续执行，而另一个线程T2则需要等待，当第T1执行完毕getInstance之后（完成了null判断、对象创建、获得返回值之后），T2线程才会执行执行。——所以这端代码也就避免了Version1中，可能出现因为多线程导致多个实例的情况。
 但是，这种写法也有一个问题：**给gitInstance方法加锁，虽然会避免了可能会出现的多个实例问题，但是会强制除T1之外的所有线程等待，实际上会对程序的执行效率造成负面影响。**

## 3.3 双重检查（Double-Check）版本

Version2代码相对于Version1d代码的效率问题，其实是为了解决1%几率的问题，而使用了一个100%出现的防护盾。那有一个优化的思路，就是把100%出现的防护盾，也改为1%的几率出现，使之只出现在可能会导致多个实例出现的地方。
 ——有没有这样的方法呢？当然是有的，改进后的代码Vsersion3如下：

```
// Version 3 
public class Single3 {
    private static Single3 instance;
    private Single3() {}
    public static Single3 getInstance() {
        if (instance == null) {
            synchronized (Single3.class) {
                if (instance == null) {
                    instance = new Single3();
                }
            }
        }
        return instance;
    }
}
```

这个版本的代码看起来有点复杂，注意其中有两次`if (instance == null)`的判断，这个叫做『双重检查 Double-Check』。

- 第一个`if (instance == null)`，其实是为了解决Version2中的效率问题，只有instance为null的时候，才进入`synchronized`的代码段——大大减少了几率。
- 第二个`if (instance == null)`，则是跟Version2一样，是为了防止可能出现多个实例的情况。

—— 这段代码看起来已经完美无瑕了。
 ……
 ……
 ……
 —— 当然，只是『看起来』，还是有小概率出现问题的。
 这弄清楚为什么这里可能出现问题，首先，我们需要弄清楚几个概念：`原子操作`、`指令重排`。

### 知识点：什么是原子操作？

简单来说，`原子操作（atomic）`就是不可分割的操作，在计算机中，就是指不会因为线程调度被打断的操作。
 比如，简单的赋值是一个原子操作：

> m = 6; // 这是个原子操作

假如m原先的值为0，那么对于这个操作，要么执行成功m变成了6，要么是没执行m还是0，而不会出现诸如m=3这种中间态——即使是在并发的线程中。

而，声明并赋值就不是一个原子操作：

> int n = 6; // 这不是一个原子操作

对于这个语句，至少有两个操作：
 ①声明一个变量n
 ②给n赋值为6
 ——这样就会有一个中间状态：变量n已经被声明了但是还没有被赋值的状态。
 ——这样，在多线程中，由于线程执行顺序的不确定性，如果两个线程都使用m，就可能会导致不稳定的结果出现。

### 知识点：什么是指令重排？

简单来说，就是计算机为了提高执行效率，会做的一些优化，在不影响最终结果的情况下，可能会对一些语句的执行顺序进行调整。
 比如，这一段代码：

```
int a ;   // 语句1 
a = 8 ;   // 语句2
int b = 9 ;     // 语句3
int c = a + b ; // 语句4
```

正常来说，对于顺序结构，执行的顺序是自上到下，也即1234。
 但是，由于`指令重排`的原因，因为不影响最终的结果，所以，实际执行的顺序可能会变成3124或者1324。
 由于语句3和4没有原子性的问题，语句3和语句4也可能会拆分成原子操作，再重排。
 ——也就是说，对于非原子性的操作，在不影响最终结果的情况下，其拆分成的原子操作可能会被重新排列执行顺序。

OK，了解了`原子操作`和`指令重排`的概念之后，我们再继续看Version3代码的问题。
 下面这段话直接从陈皓的文章([深入浅出单实例SINGLETON设计模式](https://link.jianshu.com?t=http://coolshell.cn/articles/265.html))中复制而来：

> 主要在于singleton = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
>
> 1. 给 singleton 分配内存
> 2. 调用 Singleton 的构造函数来初始化成员变量，形成实例
> 3. 将singleton对象指向分配的内存空间（执行完这步 singleton才是非 null 了）
>     但是在 JVM 的即时编译器中存在指令重排序的优化。也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是 1-2-3 也可能是 1-3-2。如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，**这时 instance 已经是非 null 了（但却没有初始化），所以线程二会直接返回 instance，然后使用，然后顺理成章地报错**。

再稍微解释一下，就是说，由于有一个**『instance已经不为null但是仍没有完成初始化』**的中间状态，而这个时候，如果有其他线程刚好运行到第一层`if (instance == null)`这里，这里读取到的instance已经不为null了，所以就直接把这个中间状态的instance拿去用了，就会产生问题。
 这里的关键在于——**线程T1对instance的写操作没有完成，线程T2就执行了读操作**。

## 3.4 终极版本：volatile

对于Version3中可能出现的问题（当然这种概率已经非常小了，但毕竟还是有的嘛~），解决方案是：只需要给instance的声明加上`volatile`关键字即可，Version4版本：

```
// Version 4 
public class Single4 {
    private static volatile Single4 instance;
    private Single4() {}
    public static Single4 getInstance() {
        if (instance == null) {
            synchronized (Single4.class) {
                if (instance == null) {
                    instance = new Single4();
                }
            }
        }
        return instance;
    }
}
```

`volatile`关键字的一个作用是禁止`指令重排`，把instance声明为`volatile`之后，对它的写操作就会有一个`内存屏障`（[什么是内存屏障？](https://link.jianshu.com?t=http://www.cnblogs.com/Mainz/p/3556430.html)），这样，在它的赋值完成之前，就不用会调用读操作。

> 注意：volatile阻止的不*singleton = new Singleton()*这句话内部[1-2-3]的指令重排，而是保证了在一个写操作（[1-2-3]）完成之前，不会调用读操作（`if (instance == null)`）。

——也就彻底防止了Version3中的问题发生。
 ——好了，现在彻底没什么问题了吧？
 ……
 ……
 ……
 好了，别紧张，的确没问题了。大名鼎鼎的[EventBus](https://link.jianshu.com?t=https://github.com/greenrobot/EventBus)中，其入口方法`EventBus.getDefault()`就是用这种方法来实现的。
 ……
 ……
 ……
 不过，非要挑点刺的话还是能挑出来的，就是这个写法有些复杂了，不够优雅、简洁。
 （傲娇脸）( ￣ー￣)

# 4. 饿汉式单例

下面再聊了解一下饿汉式的单例。

如上所说，`饿汉式`单例是指：指全局的单例实例在类装载时构建的实现方式。

由于类装载的过程是由类加载器（ClassLoader）来执行的，这个过程也是由JVM来保证同步的，所以这种方式先天就有一个优势——能够免疫许多由多线程引起的问题。

## 4.1 饿汉式单例的实现方式

`饿汉式`单例的实现如下：

```
//饿汉式实现
public class SingleB {
    private static final SingleB INSTANCE = new SingleB();
    private SingleB() {}
    public static SingleB getInstance() {
        return INSTANCE;
    }
}
```

对于一个饿汉式单例的写法来说，它基本上是完美的了。
 所以它的缺点也就只是饿汉式单例本身的缺点所在了——由于INSTANCE的初始化是在类加载时进行的，而类的加载是由ClassLoader来做的，所以开发者本来对于它初始化的时机就很难去准确把握：

1. 可能由于初始化的太早，造成资源的浪费
2. 如果初始化本身依赖于一些其他数据，那么也就很难保证其他数据会在它初始化之前准备好。

当然，如果所需的单例占用的资源很少，并且也不依赖于其他数据，那么这种实现方式也是很好的。

### 知识点：什么时候是类装载时？

前面提到了单例在**类装载时**被实例化，那究竟什么时候才是『类装载时』呢？

不严格的说，大致有这么几个条件会触发一个类被加载：

1. new一个对象时
2. 使用反射创建它的实例时
3. 子类被加载时，如果父类还没被加载，就先加载父类
4. jvm启动时执行的主类会首先被加载

（[类在什么时候加载和初始化?](https://link.jianshu.com?t=http://www.importnew.com/6579.html)）

# 5. 一些其他的实现方式

## 5.1 Effective Java 1 —— 静态内部类

《Effective Java》一书的第一版中推荐了一个中写法：

```
// Effective Java 第一版推荐写法
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    private Singleton (){}
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

这种写法非常巧妙：

- 对于内部类SingletonHolder，它是一个饿汉式的单例实现，在SingletonHolder初始化的时候会由ClassLoader来保证同步，使INSTANCE是一个真·单例。
- 同时，由于SingletonHolder是一个内部类，只在外部类的Singleton的getInstance()中被使用，所以它被加载的时机也就是在getInstance()方法第一次被调用的时候。

——**它利用了ClassLoader来保证了同步，同时又能让开发者控制类加载的时机。从内部看是一个饿汉式的单例，但是从外部看来，又的确是懒汉式的实现**。

简直是神乎其技。

## 5.2 Effective Java 2 —— 枚举

你以为到这就算完了？不，并没有，因为厉害的大神又发现了其他的方法。
 《Effective Java》的作者在这本书的第二版又推荐了另外一种方法，来直接看代码：

```
// Effective Java 第二版推荐写法
public enum SingleInstance {
    INSTANCE;
    public void fun1() { 
        // do something
    }
}

// 使用
SingleInstance.INSTANCE.fun1();
```

看到了么？这是一个枚举类型……连class都不用了，极简。
 由于创建枚举实例的过程是线程安全的，所以这种写法也没有同步的问题。

作者对这个方法的评价：

> 这种写法在功能上与共有域方法相近，但是它更简洁，无偿地提供了序列化机制，绝对防止对此实例化，即使是在面对复杂的序列化或者反射攻击的时候。虽然这中方法还没有广泛采用，但是单元素的枚举类型已经成为实现Singleton的最佳方法。

枚举单例这种方法问世一些，许多分析文章都称它是实现单例的最完美方法——写法超级简单，而且又能解决大部分的问题。
 不过我个人认为这种方法虽然很优秀，但是它仍然不是完美的——比如，在需要继承的场景，它就不适用了。

# 6. 总结

OK，看到这里，你还会觉得单例模式是最简单的设计模式了么？再回头看一下你之前代码中的单例实现，觉得是无懈可击的么？
 可能我们在实际的开发中，对单例的实现并没有那么严格的要求。比如，我如果能保证所有的getInstance都是在一个线程的话，那其实第一种最简单的教科书方式就够用了。再比如，有时候，我的单例变成了多例也可能对程序没什么太大影响……
 但是，如果我们能了解更多其中的细节，那么如果哪天程序出了些问题，我们起码能多一个排查问题的点。早点解决问题，就能早点回家吃饭……:-D

—— 还有，完美的方案是不存在，任何方式都会有一个『度』的问题。比如，你的觉得代码已经无懈可击了，但是因为你用的是JAVA语言，可能ClassLoader有些BUG啊……你的代码谁运行在JVM上的，可能JVM本身有BUG啊……你的代码运行在手机上，可能手机系统有问题啊……你生活在这个宇宙里，可能宇宙本身有些BUG啊……o(╯□╰)o
 所以，尽力做到能做到的最好就行了。

作者：腾儿飞

链接：https://www.jianshu.com/p/eb30a388c5fc

來源：简书

简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。