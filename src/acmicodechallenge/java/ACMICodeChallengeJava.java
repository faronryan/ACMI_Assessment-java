/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acmicodechallenge.java;

/**
 *
 * @author faronr
 */
public class ACMICodeChallengeJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CodeChallenge cc = new CodeChallenge();
        System.out.println(cc.netmask_to_bits("255.255.251.0"));
    }
}
