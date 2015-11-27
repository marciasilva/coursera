package firstCeasar;

import edu.duke.*;
import org.apache.commons.csv.*;

public class Babies {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void readOneFile (int year){
		String fname = "data/yob" + year + "txt";
		FileResource fr = new FileResource(fname);
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord rec : parser){
			int numBorn = Integer.parseInt(rec.get(2));
			String name = rec.get(0);
			String gender = rec.get(1);
		}
	}
	
	void totalBirths(FileResource fr){
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		for(CSVRecord rec : fr.getCSVParser(false)){
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			
			if(rec.get(1).equals("M")){
				totalBoys += numBorn;
			}
			else{
				totalGirls += numBorn;
			}
		}
		System.out.println("Total births: " + totalBirths);
		System.out.println("Total girls " + totalGirls);
		System.out.println("Total boys: "+ totalBoys);
	}
	
	//File is ordered 
	int getRank(int year, String name, String gender){
		int rank = 0;
		String fname = "data/yob" + year + "txt";
		FileResource fr = new FileResource(fname);
		CSVParser parser = fr.getCSVParser(false);
		for(CSVRecord rec : parser){
			if(rec.get(1).equals(gender)){
				if(rec.get(0).equals(name)){
					rank = (int) parser.getCurrentLineNumber();
					break;
				}
			}
		}
		
		return rank;
	}
}
