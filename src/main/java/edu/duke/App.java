package edu.duke;

import java.io.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        DirectoryResource dr = new DirectoryResource();
        for(File file : dr.selectedFiles())
        {
            // FileResource fr = new FileResource(file);
            // System.out.println(fr.asString());
            System.out.println(file.getName());
        }  
    }      
}
