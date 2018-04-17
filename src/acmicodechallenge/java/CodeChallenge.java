/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    final static Logger LOGGER = 
            Logger.getLogger(CodeChallenge.class.getName());
    private Properties prop_;
    
    public CodeChallenge()
    {
        prop_ = new Properties();
        try{
            LOGGER.addHandler(new FileHandler("resources/error.logs"));
        }catch(IOException ex){
            LOGGER.log(Level.WARNING, ex.getMessage());
        }   
    }
    
    int netmask_to_bits(String netmask){
        
        try{
            if(load_cidr_properites()){
                int result = check_bounds(netmask);
                return result;
            }
        }catch(OutofBoundsError e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch(IOException ex){
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
        
        return -1;
    }
    
    boolean load_cidr_properites() throws IOException {
        InputStream input = null;
        
        try{
            input = new FileInputStream("resources/netmask.properties");            
            prop_.load(input);
            if(!prop_.isEmpty())
                return true;
        }catch(IOException ex){
            LOGGER.log(Level.WARNING, ex.getMessage());
            throw ex;
        }
        finally{
            if(input != null)
                input.close();
        }
        
        return false;
    }
    
    int check_bounds(String netmask) throws OutofBoundsError {
        int i = 1;
        while(i < 33){
            String key = "cidr"+i;
            if(netmask.equals(prop_.getProperty(key))){
                return i;
            }
            i++;
        }
      
	throw new OutofBoundsError("INVALID Netmask!");
    }
    
    List<String> find_mac_address(File file, String filename, List<String> results){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String regex =  "[\\s]+[a-f0-9]{2}:[a-f0-9]{2}:[a-f0-9]{2}:"+
                              "[a-f0-9]{2}:[a-f0-9]{2}:[a-f0-9]{2}[\\s]+";
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
            LOGGER.log(Level.WARNING, e.getMessage());
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