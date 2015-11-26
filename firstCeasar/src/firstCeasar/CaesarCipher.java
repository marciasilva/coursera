package firstCeasar;

import edu.duke.*;


public class CaesarCipher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaesarCipher cc = new CaesarCipher();
		cc.testCaesar();

	}
	 public String encrypt(String input, int key) {
	        //Make a StringBuilder with message (encrypted)
	        StringBuilder encrypted = new StringBuilder(input);
	        //Write down the alphabet
	        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        //Compute the shifted alphabet
	        String shiftedAlphabet = alphabet.substring(key)+
	        alphabet.substring(0,key);
	        //Count from 0 to < length of encrypted, (call it i)
	        for(int i = 0; i < encrypted.length(); i++) {
	            //Look at the ith character of encrypted (call it currChar)
	            char currChar = encrypted.charAt(i);
	            //Find the index of currChar in the alphabet (call it idx)
	            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
	            //If currChar is in the alphabet
	            if(idx != -1){
	                //Get the idxth character of shiftedAlphabet (newChar)
	                char newChar = shiftedAlphabet.charAt(idx);
	                if(Character.isLowerCase(currChar))
	                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
	                else
	                    //Replace the ith character of encrypted with newChar
	                    encrypted.setCharAt(i, newChar);
	            }
	            //Otherwise: do nothing
	        }
	        //Your answer is the String inside of encrypted
	        return encrypted.toString();
	    }
	    
	    public boolean isVowel(char ch){
	        ch = Character.toLowerCase(ch);
	        if(ch == 'a' || ch == 'e'||  ch == 'i'||ch == 'o' || ch == 'u')
	            return true;
	        else 
	            return false;
	    }
	    
	    public void testIsVowel(){
	        System.out.println(isVowel('a'));
	        System.out.println(isVowel('F'));   
	        System.out.println(isVowel('O'));
	    }
	    
	    public String replaceVowels(String phrase, char ch){
	        StringBuilder newStr = new StringBuilder(phrase);
	        for(int i = 0; i < phrase.length(); i++){
	            if(isVowel(phrase.charAt(i))){
	                newStr.setCharAt(i,ch);
	            }
	        }
	        return newStr.toString();
	    }
	    
	    public void testReplace(){
	        System.out.println(replaceVowels("Marcia",'-'));
	    }
	    
	    public String emphasize(String phrase, char ch){
	        StringBuilder newStr = new StringBuilder(phrase);
	        ch = Character.toLowerCase(ch);
	        for(int i = 0; i < phrase.length(); i++){
	            if(Character.toLowerCase(phrase.charAt(i)) == ch){
	                if(i % 2 == 0)
	                    newStr.setCharAt(i,'*');
	                else 
	                    newStr.setCharAt(i,'+');
	            }
	        }
	        return newStr.toString();
	    }
	    
	    public void testEmphasize(){
	        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	    }
	    
	    public void testCaesar() {
	        int key = 17;
	        FileResource fr = new FileResource();
	        String message = fr.asString();
	        String encrypted = encrypt(message, key);
	        System.out.println(encrypted);
	        String decrypted = encrypt(encrypted, 26-key);
	        System.out.println(decrypted);
	        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
	         System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
	    }
	    
	    public String encryptTwoKeys(String input, int key1, int key2){
	          //Make a StringBuilder with message (encrypted)
	        StringBuilder encrypted = new StringBuilder(input);
	        //Write down the alphabet
	        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        //Compute the shifted alphabet
	        String shiftedAlphabetKey1 = alphabet.substring(key1)+
	        alphabet.substring(0,key1);
	        
	        String shiftedAlphabetKey2 = alphabet.substring(key2)+
	        alphabet.substring(0,key2);
	        //Count from 0 to < length of encrypted, (call it i)
	        for(int i = 0; i < encrypted.length(); i++) {
	            //Look at the ith character of encrypted (call it currChar)
	            char currChar = encrypted.charAt(i);
	            //Find the index of currChar in the alphabet (call it idx)
	            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
	            //If currChar is in the alphabet
	            if(idx != -1){
	                //Get the idxth character of shiftedAlphabet (newChar)
	                char newChar;
	                if(i == 0 || i % 2 == 0){
	                     newChar = shiftedAlphabetKey1.charAt(idx);
	                 }
	                 else{
	                      newChar = shiftedAlphabetKey2.charAt(idx);
	                 }
	                 if(Character.isLowerCase(currChar)) 
	                    newChar = Character.toLowerCase(newChar);
	                    
	                 encrypted.setCharAt(i,newChar);
	            }
	            //Otherwise: do nothing
	        }
	        //Your answer is the String inside of encrypted
	        return encrypted.toString();
	    }
	    
	    public void testTwoKeys(){
	        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
	    }
	    //Exercise week one - decrypt
	     public int [] countLetters(String message){
	        String alph = "abcdefghijklmnopqrstuvwzyz";
	        int [] counts = new int [26];
	        for (int k =0; k < message.length(); k++){
	            char ch = Character.toLowerCase(message.charAt(k));
	            int dex = alph.indexOf(ch);
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
	                largest = freqs[i];
	        }
	        return largest;
	    }
	    
	    public String decrypt(String encrypted){
	        CaesarCipher cc = new CaesarCipher();
	        int [] freqs = countLetters(encrypted);
	        int maxDex = maxIndex(freqs);
	        int dkey = maxDex - 4;
	        if(maxDex < 4){
	            dkey = 26 - (4 - maxDex);
	        }
	        return cc.encrypt(encrypted, 26 - dkey);
	    }

}