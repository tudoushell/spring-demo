package com.elliot.spring.demo.drools.test;

import com.elliot.spring.demo.drools.entity.BookDiscount;
import com.elliot.spring.demo.drools.entity.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class BookDroolsTest {

    private KieSession kieSession;

    @BeforeEach
    public void init() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        kieSession = kieContainer.newKieSession();

    }

    @Test
    public void testContains() {
        City city = new City();
        city.setCountryName("china");
        city.getCityNames().add("beijing");
        city.getCityNames().add("shanghai");
        city.getCityNames().add("nj");
        kieSession.insert(city);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

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
        BookDiscount bookDiscount = new BookDiscount();
        bookDiscount.setOriginalPrice(120.0);
        kieSession.insert(bookDiscount);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(bookDiscount.getDiscountPrice());
    }
}
