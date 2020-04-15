/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirrorservera.models;

/**
 *
 * @author CHRISTIAN
 */
public class Token {
    
    private String sellerCode;
    private int[] numbers = new int[6];
    
    public Token(String seller, int[] nums){
        this. sellerCode = seller;
        this.numbers = nums;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }
    
    @Override
    public String toString(){
        
        return "Seller code: " + sellerCode + "\n" + " "
                + "Numbers: " + numbers[0] + " " + numbers[1] + " " + numbers[2] + " "
                + "" + numbers[3] + " " + numbers[4] + " " + numbers[5];
    }
}
