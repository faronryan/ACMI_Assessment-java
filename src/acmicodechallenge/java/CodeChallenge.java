/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;
import java.lang.Exception;

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
}
