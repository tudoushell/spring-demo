package com.elliot.spring.demo.drools.test;

import com.elliot.spring.demo.drools.entity.BookDiscount;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class BookDroolsTest {

    @Test
    public void test() {
        /**
         * 1. 获取KieServices
         * 2. 获取KieContainer
         * 3. KieSession
         * 4. Insert fact
         * 5. 触发规则
         * 6. 关闭KieSession
         */
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        BookDiscount bookDiscount = new BookDiscount();
        bookDiscount.setOriginalPrice(120.0);
        kieSession.insert(bookDiscount);

        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(bookDiscount.getDiscountPrice());
    }
}
