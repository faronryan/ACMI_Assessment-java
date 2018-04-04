/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Faron Ryan
 */
public class CustomDict {
    private HashMap<String, String> internal;
    private HashMap<String, String> history;
    
    public CustomDict(String dict){
        internal = new HashMap<>();
        history = new HashMap<>();
        
        String regex = "[\\s]*[a-z0-9]+[\\s]*=>[\\s]*'[a-z0-9]+'[\\s]*";
        final int flags = Pattern.CASE_INSENSITIVE;
        Pattern p = Pattern.compile(regex, flags);
         
        if(dict.length() > 0) {   
            Matcher m = p.matcher(dict);
            while (m.find()) { 
                String entry = m.group(0);
                String key = entry.substring(0, entry.indexOf("="));
                key = key.trim();
                String value = entry.substring(entry.indexOf(">")+1, 
                                               entry.length()-1);
                value = value.trim().replace("'", "");
                internal.put(key,value);
            }            
        }   
    }

    String get(String key) {
        return internal.get(key);
    }

    void add(String key_value) {
        String entry = key_value;
        String key = entry.substring(0, entry.indexOf("="));
        key = key.trim();
        String value = entry.substring(entry.indexOf(">")+1, 
                                       entry.length()-1);
        value = value.trim().replace("'", "");
        internal.put(key,value);
        history.put(key,"ADD "+key+" = "+value);  
    }

    void delete_(String key) {
        internal.remove(key);
        history.put(key,"DELETE "+key);
    }

    void modify(String key_value) {
        // Could have packed below 5 lines in another 
        // function since I've used it 3 times in total
        String entry = key_value;
        String key = entry.substring(0, entry.indexOf("="));
        key = key.trim();
        String value = entry.substring(entry.indexOf(">")+1, 
                                       entry.length()-1);
        value = value.trim().replace("'", "");
        internal.put(key,value);
        history.put(key,"MODIFY "+key+" = "+value);  
    }

    String [] deltas() { 
        String [] array = new String[history.size()];
        Iterator it = history.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getValue()); 
            array[i++] = (String)pair.getValue();
        }  
        return array; // Only returning to validate test case
    }    
}
