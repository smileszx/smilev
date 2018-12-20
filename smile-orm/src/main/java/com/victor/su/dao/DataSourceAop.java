package com.victor.su.dao;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

@Aspect
@Order(-1)
@Component
public class DataSourceAop {


    @Before("execution(* com.victor.su.dao.dbone..*.*(..))")
    public void setCenterUserDataSourceType() throws Exception {
        DbContextHolder.setDbType(DBTypeEnum.dbone);
    }

    @Before("execution(* com.victor.su.dao.dbtwo..*.*(..))")
    public void setUserCenterDataSourceType() throws Throwable{
        DbContextHolder.setDbType(DBTypeEnum.dbtwo);
    }


    @After("execution(* com.victor.su.dao.*..*.*(..)) ")
    public void afterReturning() throws Throwable {
        DbContextHolder.setDbType(DBTypeEnum.dbone);
    }
}

