/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Exception;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Faron Ryan
 */

class OutofBoundsError extends Exception{
    public OutofBoundsError(String message){
        super(message);
    }
}
 
public class CodeChallenge {
    private final String [] NETMASK_LOOKUP_TABLE =
        {"128.0.0.0","192.0.0.0","224.0.0.0","240.0.0.0",
	"248.0.0.0","252.0.0.0","254.0.0.0","255.0.0.0",
	"255.128.0.0","255.192.0.0","255.224.0.0",
	"255.240.0.0","255.248.0.0","255.252.0.0","255.254.0.0",
	"255.255.0.0","255.255.128.0","255.255.192.0",
	"255.255.224.0","255.255.240.0","255.255.248.0",
	"255.255.252.0","255.255.254.0","255.255.255.0",
	"255.255.255.128","255.255.255.192","255.255.255.224",
	"255.255.255.240","255.255.255.248","255.255.255.252",
	"255.255.255.254","255.255.255.255"};

    public CodeChallenge()
    {}
    
    int netmask_to_bits(String netmask){
        try{
            int result = check_bounds(netmask);
            return result;
        }catch(OutofBoundsError e){
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    int check_bounds(String netmask) throws OutofBoundsError {
        int i=0;
        for(String s: NETMASK_LOOKUP_TABLE){
            if(s.equals(netmask))
                return i+1; // base-1 index
            i++;
	}

	throw new OutofBoundsError("INVALID Netmask!");
    }
    
    List<String> find_mac_address(File file, String filename, List<String> results){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String regex =  "[\\s]+[a-f0-9_]{2}:[a-f0-9_]{2}:[a-f0-9_]{2}:"+
                              "[a-f0-9_]{2}:[a-f0-9_]{2}:[a-f0-9_]{2}[\\s]+";
            final int flags = Pattern.CASE_INSENSITIVE;
            Pattern p = Pattern.compile(regex, flags);
            
            String line;
            results = new ArrayList();
            int i = 0;
            while((line = br.readLine()) != null){
                Matcher m = p.matcher(line);
                while (m.find()) { 
                    String output = "File: "+filename+" line["+i+"] "+ 
                                    " mac_address="+m.group(0);
                    results.add(output);
                }
                i++;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
        return results;
    }
     
    Map<String,Object> explodereport(List<String> rawinput){
        Map<String,Object> dumper = new HashMap<>();
          
        for(String line: rawinput){ 
            String[] keys = line.replace('|', ',').split(",");
            exploder_helper(Arrays.asList(keys), 0, dumper);
        }
         
        return dumper;
    }
    
    Object exploder_helper(List<String> lst, int index, 
                                           Object new_hash){
    
        if( index < lst.size() -1)
        { 
            Map hsh = (HashMap)new_hash;
            if(hsh.containsKey(lst.get(index))){ 
                hsh.put(lst.get(index),(HashMap)exploder_helper(lst, index+1,
                        hsh.get(lst.get(index))));
                return hsh;
            }
            else{
                hsh.put(lst.get(index),exploder_helper(lst, index+1,
                        new HashMap<>()));
                return hsh; 
            }
        }
        else{ 
            return lst.get(index);
        }
    }
}