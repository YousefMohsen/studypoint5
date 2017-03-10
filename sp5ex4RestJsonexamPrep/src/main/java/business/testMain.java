/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Yousinho
 */
public class testMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DataGenerator dg = new DataGenerator();
        System.out.println(dg.getData(10, "fname,lname,city"));
        // TODO code application logic here
    }
    
}
