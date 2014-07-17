package com.cj.nan.koans.java.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import junit.framework.TestCase;

import org.junit.Test;

public class CollectionsTest extends TestCase {
    
	private static final String[] keys;
	private static final String[] values;
	
	static {
		keys = new String[] {"key1", "key2", null};
		values = new String[] {"val1", "val2", null};
	}
	
	
    @Test
    public void testMaps() {
    	
        Map<String, String> theMap = new HashMap<String, String>();
        theMap.put("key1", "something");
        theMap.put("key1", "val1");
        theMap.put("key2", "val2");
        theMap.put(null, null);
        assertEquals(theMap.size(), 3);
        
        Set<Map.Entry<String,String>> theSetOfHashMapEntries = theMap.entrySet();
        int i = 2;
        for(Map.Entry<String, String> entry : theSetOfHashMapEntries) {
        	assertEquals(keys[i], entry.getKey());
        	assertEquals(values[i], entry.getValue());
        	i--;
        }
        
        i = 2;
        for(String value : theMap.values()) {
        	assertEquals(values[i], value);
        	i--;
        }

        Map<String, String> treeMap = new HashMap<String, String>();
        treeMap.put("key1", "something");
        treeMap.put("key1", "val1");
        treeMap.put("key2", "val2");
        //treeMap.put(null, null); --> cannot put null
        assertEquals(treeMap.size(), 2);
        
        Set<Map.Entry<String,String>> theSetOfTreeMapEntries = theMap.entrySet();
        i = 2;
        for(Map.Entry<String, String> entry : theSetOfTreeMapEntries) {
        	assertEquals(keys[i], entry.getKey());
        	assertEquals(values[i], entry.getValue());
        	i--;
        }
        
        i = 1;
        for(String value : treeMap.values()) {
        	assertEquals(values[i], value);
        	i--;
        }
    }
    
    @Test
    public void testList() {
    	ArrayList<String> arrayList = new ArrayList<String>();
    	arrayList.add("val1");
    	arrayList.add("val2");
    	arrayList.add(null);

    	iterateOver(arrayList);
    	
    	LinkedList<String> linkedList = new LinkedList<String>();
    	linkedList.add("val1");
    	
    }
    
    private void iterateOver(Collection<String> strings) {
    	int i = 0;
    	for(Iterator<String> stringIter = strings.iterator(); stringIter.hasNext(); ) {
    		assertEquals(values[i],stringIter.next());
    		i++;
    	}
    	i=0;
    	for(String theString : strings) {
    		assertEquals(values[i], theString);
    		i++;
    	}
    }
}
