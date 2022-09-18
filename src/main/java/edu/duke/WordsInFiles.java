package edu.duke;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles {
    
    private HashMap<String, ArrayList<String>> wordFileMap;
    
    public WordsInFiles(){
        wordFileMap = new HashMap<String, ArrayList<String>>();
        
    }
    
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String currFileName = f.getName();
        for(String word : fr.words()){
            if(wordFileMap.containsKey(word)){
                ArrayList<String> fileName = wordFileMap.get(word);
                if(!fileName.contains(currFileName)){
                    wordFileMap.put(word,fileName);
                    fileName.add(currFileName);
                    
                }
            }
            else {
                ArrayList<String> fileName = new ArrayList<String>();
                wordFileMap.put(word,fileName);
                fileName.add(currFileName);
                
                
                
            }
            
        }
        
        // System.out.println(wordFileMap);
    }
    
    public void buildWordFileMap(){
        wordFileMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        // System.out.println(wordFileMap.keySet());
        int counter = 0;
        for (String s : wordFileMap.keySet()) {
                // System.out.println(s+"\t" + wordFileMap.get(s));
                if(wordFileMap.get(s).size() > counter){
                    counter=wordFileMap.get(s).size();
                }
                // System.out.println(s+ "\t"+wordFileMap.get(s).size());   
        }
        return counter;
    }
    
    public ArrayList<String> wordsInNumFiles(int exactNum){
        ArrayList<String> wordsInNumFiles = new ArrayList<String>();
        for (String s : wordFileMap.keySet()) {
            if (wordFileMap.get(s).size() == exactNum) {
                wordsInNumFiles.add(s);
            }
        }

        return wordsInNumFiles;
    }

    public void printFilesIn(String word){
        for (String s : wordFileMap.keySet()) {
            if (s.equals(word)) {
                ArrayList<String> arr = wordFileMap.get(s);
                for(int i = 0; i<arr.size(); i++){
                    System.out.println(arr.get(i));
                }
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        // System.out.println(wordFileMap);
        // 
        int maxNum = maxNumber();
        // System.out.println(maxNum);

        System.out.println(wordsInNumFiles(4).size());

        // printFilesIn("cats");

        // System.out.println(wordFileMap);
    }
    

}
