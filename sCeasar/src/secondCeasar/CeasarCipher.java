

package secondCeasar;


import edu.duke.*;

public class CeasarCipher {
	
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;

	
	//This method should initialize all the private fields of the class
	public CeasarCipher(int key){
		this.alphabet = "abcdefghijklmnopqrstuvwxyz";
		this.shiftedAlphabet = alphabet.substring(key)+
		        alphabet.substring(0,key);
		this.mainKey = key;
	}
	
	public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = this.alphabet.indexOf(Character.toLowerCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = this.shiftedAlphabet.charAt(idx);
                if(Character.isUpperCase(currChar))
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                else
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
	}
		
	public String decrypt(String input){
        CeasarCipher cc = new CeasarCipher(26 - mainKey); 
        return cc.encrypt(input);
	}
	
	   //Exercise week one - decrypt
    public int [] countLetters(String message){	
       int [] counts = new int [26];
       for (int k =0; k < message.length(); k++){
           char ch = Character.toLowerCase(message.charAt(k));
           int dex =  this.alphabet.indexOf(ch);
           if(dex != -1){
               counts[dex] += 1;
           }
       }
       return counts;
   }
   
   //Consider the English most common letter E
   public int maxIndex(int [] freqs){
       int largest = 0;
       for (int i = 0; i < freqs.length; i++){
           if(freqs[i] > freqs[largest])
               largest = i;
       }
       return largest;
   }

   public String breakCeasarCipher(String input){
	   int [] freqs = countLetters(input);
       int maxDex = maxIndex(freqs);
       int dkey = maxDex - 4;
       if(maxDex < 4){
           dkey = 26 - (4 - maxDex);
       }
	   CeasarCipher cc = new CeasarCipher(26-dkey);
       return cc.encrypt(input);
   }
   
   public void simpleTests(CeasarCipher cc){
	   FileResource fr = new FileResource();
       String message = fr.asString();
       System.out.println("Encrypted: " + cc.encrypt(message));
       System.out.println("Decrypted: " + cc.breakCeasarCipher(cc.encrypt(message)));
   }

   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    CeasarCipher cc = new CeasarCipher(17);
	    CeasarCipherTwo cct = new CeasarCipherTwo(0,0);
	    FileResource fr = new FileResource();
	    String message = fr.asString();
	  
	   //System.out.println("Encrypted two: " + cct.encrypt(message));
	    //System.out.println("Decrypted two: " + cct.decrypt(cct.encrypt(message)));
	    System.out.println("Decrypted two: " + cct.breakCeasarCipher(cct.encrypt(message)));

	    //cc.simpleTests(cc);
	}

}
