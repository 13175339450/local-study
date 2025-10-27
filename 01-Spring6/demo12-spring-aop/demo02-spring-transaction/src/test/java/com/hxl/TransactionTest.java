package com.hxl;

import com.customize.autoConfigure.ServiceConfiguration;
import com.customize.service.MySQLService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {

    @Test
    public void test() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ServiceConfiguration.class);

        MySQLService mysql = context.getBean("mySQLService", MySQLService.class);
        mysql.insert();
        mysql.update();
    }
}
