package firstCeasar;

import edu.duke.*;

public class CommonWords {

    public String[] getCommon(){
        FileResource resource = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for(String s : resource.words()){
            common[index] = s;
            index += 1;
        }
        return common;
    }
    
    public int indexOf(String[] list, String word) {
        for (int k=0; k<list.length; k++) {
            if (list[k].equals(word)) {
                   return k;
               }
           }
        return -1;
    }
    
    public void countWords(FileResource resource, String[] common, int[] counts){
        for(String word : resource.words()){
            word = word.toLowerCase();
            int index = indexOf(common,word);
            if (index != -1) {
                counts[index] += 1;
            }
        }
    }
    void countShakespeare(){
        //String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
            //          "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] plays = {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int k=0; k < plays.length; k++){
            FileResource resource = new FileResource("data/" + plays[k]);
            countWords(resource,common,counts);
            System.out.println("done with " + plays[k]);
        }
        
        for(int k=0; k < common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);
        }
    }
    void countWordLengths(FileResource resource, int[]counts){
        int [] sizeWords;
        sizeWords =  new int[counts.length];
        for (String word : resource.words() ) {
             int wordlength = word.length();   
            if (Character.isLetter(word.charAt(word.length()-1)) == false) {
                wordlength--;
            }
            if(Character.isLetter(word.charAt(0)) == false){
                wordlength--;
            }
            if (wordlength >= counts.length) {                 
                wordlength = counts.length - 1;         
            }   
            if (wordlength > 0 ) {             
                counts[wordlength] ++;          
            }
        }
    }

    public void testCountWordLengths (){
        FileResource resource = new FileResource();
        int [] counts = new int [31];
        countWordLengths(resource, counts);

        for (int i = 1; i < counts.length; i++){
            System.out.println( counts[i] + " of length " + i );
        }

    }
}
