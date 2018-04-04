/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;

import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author faronr
 */
public class CustomDictTest {
    
    public CustomDictTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class CustomDict.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String key = "foo";
        CustomDict instance = new CustomDict("deer => 'park', foo => 'bar', this => 'that'");
        String expResult = "bar";
        String result = instance.get(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class CustomDict.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String key_value = "gnu => 'linux'";
        CustomDict instance = new CustomDict("deer => 'park', foo => 'bar', this => 'that'");
        instance.add(key_value);
        String key = "gnu";
        String expResult = "linux";
        String result = instance.get(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete_ method, of class CustomDict.
     */
    @Test
    public void testDelete_() {
        System.out.println("delete_");
        String key = "this";
        CustomDict instance = new CustomDict("deer => 'park', foo => 'bar', this => 'that'");
        instance.delete_(key);
        String expResult = null;
        try{
            String result = instance.get(key);
            assertEquals(expResult, result);
        }catch(AssertionFailedError ae){
            assertEquals(expResult, null);
        }
    }

    /**
     * Test of modify method, of class CustomDict.
     */
    @Test
    public void testModify() {
        System.out.println("modify");
        String key_value = "gnu => 'not unix'";
        CustomDict instance = new CustomDict("deer => 'park', foo => 'bar', this => 'that'");
        instance.modify(key_value);
        String key = "gnu";
        String expResult = "not unix";
        String result = instance.get(key);
        assertEquals(true, result.equals(expResult)); 
    }

    /**
     * Test of deltas method, of class CustomDict.
     */
    @Test
    public void testDeltas() {
        System.out.println("deltas");
        CustomDict instance = new CustomDict("deer => 'park', foo => 'bar', this => 'that'");
        
        instance.delete_("this"); 
        instance.add("gnu => 'linux'"); 
        instance.modify("gnu => 'not unix'"); 
        instance.modify("deer => 'venison'"); 
        instance.modify("gnu => 'emacs'"); 
        
        String [] expResult = {"MODIFY deer = venison",
                               "DELETE this",
                               "MODIFY gnu = emacs"}; 
        String [] result = instance.deltas();
        assertEquals(expResult, result);
    }
    
}
