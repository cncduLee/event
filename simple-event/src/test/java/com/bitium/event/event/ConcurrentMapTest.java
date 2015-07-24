/**
 * Copyright (c) 2015, jdpay.com. All rights reserved.
 */
package com.bitium.event.event;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： com.jdpay.event.event <br>
 * <b>类名称</b>： ConcurrentMapTest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/22 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ConcurrentMapTest {

    @Test
    public void testPutIfAbsent() {
        ConcurrentMap<String,String> cm = new ConcurrentHashMap<String, String>();
        String value = cm.putIfAbsent("a","b");
        Assert.assertNull(value);
        value = cm.get("a");
        Assert.assertEquals("b",value);
    }

    @Test
    public void addAfterGet() {
        ConcurrentMap<String,CopyOnWriteArraySet<String>> cm = new ConcurrentHashMap<String, CopyOnWriteArraySet<String>>();

        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        cm.putIfAbsent("a",set);

        CopyOnWriteArraySet<String> getSet = cm.get("a");
        getSet.add("d");

        Assert.assertTrue(cm.get("a").contains("d"));
    }

    @Test
    public void addAfterGetForSet() {
        ConcurrentMap<String,Set<String>> cm = new ConcurrentHashMap<String, Set<String>>();

        Set<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        cm.putIfAbsent("a",set);

        Set<String> getSet = cm.get("a");
        getSet.add("d");

        Assert.assertTrue(cm.get("a").contains("d"));
    }

    @Test
    public void addAfterGetForList() {
        ConcurrentMap<String,List<String>> cm = new ConcurrentHashMap<String, List<String>>();

        List<String> set = new ArrayList<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        cm.putIfAbsent("a",set);

        List<String> getSet = cm.get("a");
        getSet.add("d");

        Assert.assertTrue(cm.get("a").contains("d"));
    }

    @Test
    public void addAfterGetForMap() {
        Map<String,List<String>> cm = new HashMap<String, List<String>>();

        List<String> set = new ArrayList<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        cm.put("a",set);

        List<String> getSet = cm.get("a");
        getSet.add("d");

        Assert.assertTrue(cm.get("a").contains("d"));
    }
}
