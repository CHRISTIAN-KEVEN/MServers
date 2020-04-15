/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirrorservera;

/**
 *
 * @author CHRISTIAN
 */
public class Main {
    
    public static void main(String... args){
       
            
            MirrorServerA msA = new MirrorServerA();
            MirrorServerB msB = new MirrorServerB();
            
            msA.go();    
            msB.go();
            
            System.out.println("Successfully spowned threads ");
            
       
        
    }
}
