package edu.GladLibs;

import edu.duke.*;
import java.util.*;

public class GladLibMap {

	private HashMap <String,ArrayList<String>> myMap;
	private ArrayList<ArrayList<String>> arr;

	// private ArrayList<String> adjectiveList;
	// private ArrayList<String> nounList;
	// private ArrayList<String> colorList;
	// private ArrayList<String> countryList;
	// private ArrayList<String> nameList;
	// private ArrayList<String> animalList;
	// private ArrayList<String> timeList;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		arr = new ArrayList<>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {

		String[] labels = {"country","noun","animal","adjective","name","color","timeframe","fruit","verb"};
		
		for (String s : labels) {
			ArrayList<String> list = readIt(source+"/"+s+".txt");
			myMap.put(s, list);
		}
		// adjectiveList= readIt(source+"/adjective.txt");	
		// nounList = readIt(source+"/noun.txt");
		// colorList = readIt(source+"/color.txt");
		// countryList = readIt(source+"/country.txt");
		// nameList = readIt(source+"/name.txt");		
		// animalList = readIt(source+"/animal.txt");
		// timeList = readIt(source+"/timeframe.txt");		
	}
	
	private String randomFrom(ArrayList<String> source){
		
		if (!arr.contains(source)) {
			arr.add(source);
		}
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return randomFrom(myMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		// ArrayList <ArrayList<String>> arr = new ArrayList<>();
		// int counter = 0;
		// if (!arr.contains(myMap.get(w.substring(first+1,last)))) {
		// 	System.out.println(myMap.get(w.substring(first+1,last)));
		// 	arr.add(myMap.get(w.substring(first+1,last)));
		// 	// System.out.println(arr);
		// 	// counter = counter+1;
		// }
		// System.out.println("Considered " +counter);
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
			
		}
		return list;
	}

	public int totalWordsInMap(){
		int total= 0;
		ArrayList <ArrayList<String>> arr = new ArrayList<>();
		for (String word : myMap.keySet()) {
			if (!arr.contains(myMap.get(word))) {
				total = total + myMap.get(word).size();
				arr.add(myMap.get(word));
			}
			
		}
		return total;
	}

	public int totalWordsConsidered(){
		int counter1 = 0;
		for(int i=0; i<arr.size();i++){
			int size =  arr.get(i).size();
			counter1 = counter1 + size;
		}
		return counter1;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate.txt");
		printOut(story, 60);
		System.out.println("\n" + "total number of words that were possible to pick from is "
							+totalWordsInMap());
		System.out.println("totalWordsConsidered "+totalWordsConsidered());
		
	}
	


}
