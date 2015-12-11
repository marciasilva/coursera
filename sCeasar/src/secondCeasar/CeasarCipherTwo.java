package secondCeasar;

public class CeasarCipherTwo {
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int mainKey1;
	private int mainKey2;
	
	
	public CeasarCipherTwo(int key1, int key2){
		this.alphabet = "abcdefghijklmnopqrstuvwxyz";
		this.shiftedAlphabet1 = alphabet.substring(key1)+
		        alphabet.substring(0,key1);
		this.shiftedAlphabet2 = alphabet.substring(key2)+
		        alphabet.substring(0,key2);
		this.mainKey1 = key1;
		this.mainKey2 = key2;
		 //System.out.println(key1);
		// System.out.println(key2);
	}
	
	 public String encrypt(String input){
         //Make a StringBuilder with message (encrypted)
		 StringBuilder encrypted = new StringBuilder(input);
		 //Count from 0 to < length of encrypted, (call it i)
		 for(int i = 0; i < encrypted.length(); i++) {
			 //Look at the ith character of encrypted (call it currChar)
			 char currChar = encrypted.charAt(i);
			 //Find the index of currChar in the alphabet (call it idx)
			 int idx = this.alphabet.indexOf(Character.toLowerCase(currChar));
			 //If currChar is in the alphabet
			 if(idx != -1){
				 //Get the idxth character of shiftedAlphabet (newChar)
				 char newChar;
				 if(i == 0 || i % 2 == 0){
					 newChar = this.shiftedAlphabet1.charAt(idx);
				 }
				 else{
					 newChar = this.shiftedAlphabet2.charAt(idx);
				 }
				 if(Character.isUpperCase(currChar)) 
					 newChar = Character.toUpperCase(newChar);
                   
				 encrypted.setCharAt(i,newChar);
			 }
		 }
		 //Your answer is the String inside of encrypted
		 return encrypted.toString();
	 }
	 
	 public String decrypt(String input){
		 CeasarCipherTwo cct = new CeasarCipherTwo(26 - this.mainKey1, 26 - this.mainKey2); 
		 System.out.println(26 - this.mainKey1);
	     return cct.encrypt(input);
	 }
	 
	 String halfOfString(String message, int start){
		 String half = "";
		 for (int i = start; i < message.length(); i+=2){
	    	half += message.charAt(i);
		 }
		 return half;
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
	   
	   
	    int getKey(String input){
	    	int [] cl = countLetters(input);
	    	int maxIndex = 0;
	    	int maxValue = 0;
	    	for (int i = 0; i < cl.length; i++){
	    		if(cl[i] > cl[maxIndex]){
	    			maxIndex = i;
	    		}
	    	}
	    	char maxC = this.alphabet.charAt(maxIndex);
	    	maxIndex = this.shiftedAlphabet2.indexOf(maxC);

	    System.out.println("entrada: " + input);
	    	System.out.println(this.shiftedAlphabet2);
	    	//System.out.println(maxValue);
	    	return maxIndex;
	   }
	    
	   
	 public String breakCeasarCipher(String input){
		 String partOne = halfOfString(input,0);
		 String partTwo = halfOfString(input,1);
		 
		 int keyPartOne = this.shiftedAlphabet1.indexOf('a');
		 int keyPartTwo = this.shiftedAlphabet2.indexOf('a');
		
		 CeasarCipherTwo cct = new CeasarCipherTwo(keyPartOne, keyPartTwo);
	     return cct.encrypt(input);
	 }
}
