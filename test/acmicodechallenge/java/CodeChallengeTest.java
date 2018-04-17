/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
     * Test of netmaskToBits method, of class CodeChallenge.
     */
    @Test
    public void testNetmask_to_bits() {
        System.out.println("netmask_to_bits");
        String netmask = "255.255.252.0";
        
        int expResult = 22;
        CodeChallenge instance = new CodeChallenge();
        int result = instance.netmaskToBits(netmask);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkBounds method, of class CodeChallenge.
     */
    @Test
    public void testCheck_bounds() throws Exception {
        System.out.println("check_bounds");
        String netmask = "255.255.253.0";
        CodeChallenge instance = new CodeChallenge();
        instance.loadCIDRProperites();
        int expResult = -1;
        try {
            int result = instance.checkBounds(netmask);
            assertEquals(expResult, result);
        }catch(OutofBoundsError e){
            assertEquals(true, true); 
        }        
    }
    
    /**
     * Test of findMACAddress method, of class CodeChallenge.
     */
    @Test
    public void testFind_mac_address() { 
        System.out.println("find_mac_address");
        try{
            File folder = new File("test/acmicodechallenge/java/inputs");                
            File[] list = folder.listFiles();
            //System.out.println(new File("").getAbsolutePath());
            CodeChallenge instance = new CodeChallenge(); 
            instance.loadCIDRProperites();
            int [] expected_size = {7}; // one file was checked
            for(int i = 0; i < list.length; i++){

                List<String> results = null;
                if(list[i].isFile())
                    results = instance.findMACAddress(list[i], list[i].getName(), results);  
                assertEquals(expected_size[i], results.size());
            }
        }catch(IOException ex){
            assertEquals("", ex.getMessage());
        }
    }

    /**
     * Test of explodeReport method, of class CodeChallenge.
     */
    @Test
    public void testExplodereport() {
        System.out.println("explodereport");
        List<String> rawinput = new ArrayList<>();
        rawinput.add("app1|server1|uptime|5"); 
        rawinput.add("app1|server1|loadavg|0.01 0.02 0.03");
        rawinput.add("app1|server1|conn1|state|up");
        rawinput.add("app1|server2|uptime|10");
        rawinput.add("app1|server2|loadavg|0.11 0.22 0.33"); 
        rawinput.add("app1|server2|conn1|state|down"); 
        rawinput.add("app1|running|true");
        
        CodeChallenge instance = new CodeChallenge();
        Map<String, Object> result = instance.explodeReport(rawinput);
        
        Map<String, Object> conn1 = new HashMap<>();
        conn1.put("state","up");
        Map<String, Object> server1 = new HashMap<>();
        server1.put("conn1",conn1);
        server1.put("loadavg","0.01 0.02 0.03");
        server1.put("uptime","5");
        
        Map<String, Object> conn2 = new HashMap<>();
        conn2.put("state","down");
        Map<String, Object> server2 = new HashMap<>();
        server2.put("conn1",conn2);
        server2.put("loadavg","0.11 0.22 0.33");
        server2.put("uptime","10");
        
        Map<String, Object> app1 = new HashMap<>();
        app1.put("server1",server1);
        app1.put("server2",server2); 
        app1.put("running", "true");        
        /*{"app1": {"running": "true", 
            "server1": {"uptime": "5", 
                        "loadavg": "0.01 0.02 0.03", 
                        "conn1": {"state": "up"}}, 
            "server2": {"uptime": "10", 
                        "loadavg": "0.11 0.22 0.33", 
                        "conn1": {"state": "down"}}}};*/
 
        Map<String, Object> expected = new HashMap<>();
        expected.put("app1", app1);        
        
        assertEquals(expected, result);
    }

    /**
     * Test of exploderHelper method, of class CodeChallenge.
     */
    @Test
    public void testExploder_helper() { // AutoGenerated not used
        System.out.println("exploder_helper");
        List<String> lst = null;
        int index = 0;
        Object new_hash = null;
        CodeChallenge instance = new CodeChallenge();
        //Object expResult = null;
        //Object result = instance.exploderHelper(lst, index, new_hash);
        //assertEquals(expResult, result);
    }

    /**
     * Test of loadCIDRProperites method, of class CodeChallenge.
     */
    @Test
    public void testLoad_cidr_properites() throws Exception {
        CodeChallenge instance = new CodeChallenge();
        boolean expResult = true;
        boolean result = instance.loadCIDRProperites();
        assertEquals(expResult, result);
    }    
}
