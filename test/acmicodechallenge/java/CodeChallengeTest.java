/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;

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
public class CodeChallengeTest {
    
    public CodeChallengeTest() {
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
     * Test of netmask_to_bits method, of class CodeChallenge.
     */
    @Test
    public void testNetmask_to_bits() {
        System.out.println("netmask_to_bits");
        String netmask = "255.255.252.0";
        
        int expResult = 22;
        CodeChallenge instance = new CodeChallenge();
        int result = instance.netmask_to_bits(netmask);
        assertEquals(expResult, result);
    }

    /**
     * Test of check_bounds method, of class CodeChallenge.
     */
    @Test
    public void testCheck_bounds() throws Exception {
        System.out.println("check_bounds");
        String netmask = "255.255.253.0";
        CodeChallenge instance = new CodeChallenge();
        boolean expResult = true;
        try {
            int result = instance.check_bounds(netmask);
            assertEquals(expResult, false);
        }catch(OutofBoundsError e){
            assertEquals(expResult, true);
        }        
    }
    
}
