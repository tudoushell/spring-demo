package com.elliot.spring.demo.drools.test;

import com.elliot.spring.demo.drools.entity.BookDiscount;
import com.elliot.spring.demo.drools.entity.City;
import com.elliot.spring.demo.drools.entity.Staff;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.concurrent.TimeUnit;

public class DroolsTest {

    private KieSession kieSession;

    private KieServices kieServices;

    private KieContainer kieContainer;

    @BeforeEach
    public void init() {
        //必须在创建 KieContainer 之前执行 System.setProperty(),不然时间格式不生效
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.newKieClasspathContainer();
        kieSession = kieContainer.newKieSession();
    }


    @Test
    public void noLoopTest() {
        Staff staff = new Staff();
        staff.setName("1");
        kieSession.insert(staff);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testAutoFocus() {
        //自动获取焦点
        kieSession.fireAllRules();
        kieSession.dispose();
    }


    @Test
    public void testTimer() throws InterruptedException {
        //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
        new Thread(() -> {
            kieSession.fireUntilHalt();
        }, "timer-thread").start();
        TimeUnit.SECONDS.sleep(10);
        kieSession.halt();
        kieSession.dispose();
    }

    @Test
    public void testAgendaGroup() {
        //需要设置焦点
        kieSession.getAgenda()
                .getAgendaGroup("1")
                .setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testDateEffective() {
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testSalience() {
        //salience 数值越大执行优先级越高
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testRetract() {
        Staff staff = new Staff();
        staff.setLevel(4);
        staff.setSalary(4000);
        kieSession.insert(staff);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(staff.getName() + " " + staff.getLevel() + " " + staff.getSalary());
    }


    @Test
    public void testInsert() {
        Staff staff = new Staff();
        staff.setLevel(2);
        kieSession.insert(staff);
        kieSession.fireAllRules();
        kieSession.dispose();
        System.out.println(staff.getName() + " " + staff.getLevel() + " " + staff.getSalary());
    }

    @Test
    public void testUpdate() {
        Staff staff = new Staff();
        staff.setLevel(1);
        staff.setName("mike");
        kieSession.insert(staff);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    @Test
    public void testSpecifyRuleName() {
        City city = new City();
        city.setCountryName("china");
        city.getCityNames().add("beijing");
        city.getCityNames().add("shanghai");
        city.getCityNames().add("nj");
        kieSession.insert(city);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("_test"));
        kieSession.dispose();
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
