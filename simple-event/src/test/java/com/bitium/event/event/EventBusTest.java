package com.bitium.event.event;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;


public class EventBusTest {
    EventBus eventBus;

    @BeforeMethod
    public void init() {
        eventBus = new EventBus();
    }

    @Test
    public void testProduce() throws Exception {
        Sub sub = new Sub() ;
        eventBus.register(sub);
        eventBus.produce("event");
        eventBus.produce(1L);
        eventBus.produce(1.00f);
        assertTrue(3 != sub.getEvents().size());//并发执行，发送完成后马上去统计消费数量，这时还没有消费完成
    }


}