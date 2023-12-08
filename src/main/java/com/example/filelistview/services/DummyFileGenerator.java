package com.example.filelistview.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * This Class is needed for generating example files for List Tree Views
 * You can define how many good file-s you need and how many mistaken
 * It can handle for deleting the template files when you don't need it anymore
 */
@Service
public class DummyFileGenerator {
    private Random random;

    public DummyFileGenerator(){
        random = new Random();
    }
    public DummyFileGenerator(long Seed){
        random = new Random(Seed);
    }
    /**
     * Mark the folder for deletion
     * @param path Is the location of the folder
     */
    public void DeleteDummyFiles(String path){
         new File(path).deleteOnExit();
    }

    /**
     * Create a file in a specific folder, with a parent and a children
     * @param path The location of the file
     * @param fileName The name of the file
     * @param parent The parent of the file
     * @param children The children of the file
     */
    private void CreateFile(String path, String fileName, String parent, String children) {
        try {
            FileWriter fileWriter = new FileWriter(path + fileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("P:%s\n", parent);
            printWriter.printf("C:%s", children);
            printWriter.close();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with file creation");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a random string with a specified length
     * @param length The length of the string
     * @return A string full of random alphabetical character
     */
    private String GetRandomString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return  buffer.toString();
    }

    /**
     * It will generate the example files.
     * @param numbers The number of the good files
     * @param mistakes The number of the mistaken files
     * @return A path to the temporary folder which contains those files.
     */
    public String GenerateDummyFiles(Integer numbers, Integer mistakes) {
        String path;
        try {
            path = Files.createTempDirectory("generatedFiles").toFile().getAbsolutePath() + "\\";
        }catch (java.io.IOException e){
            return  "";
        }
        if(numbers < 1 && mistakes < 1) {
            return path;
        }

        int length = (int) Math.ceil(Math.log(numbers + mistakes) / Math.log(26));
        if(length == 0) length = 1;

        Set<String> filenames = new HashSet<String>();
        while(filenames.size() < numbers + mistakes) {
            filenames.add(GetRandomString(length) + ".txt");
        }
        System.out.println(filenames);
        ArrayList<String> names = new ArrayList<>(filenames);
        Collections.shuffle(names);
        System.out.println(names);


        if(numbers == 1) {
            CreateFile(path, names.get(0), "", "");
        }else if (numbers > 1){
            CreateFile(path, names.get(0), "", names.get(1));
            for (int i = 1; i < numbers-1; i++) {
                CreateFile(path, names.get(i), names.get(i-1), names.get(i+1));
            }
            CreateFile(path, names.get(numbers-1), names.get(numbers-2), "");
        }
        for (int i = 0; i < mistakes; i++) {
            int p = (int)(Math.random() * ( numbers + mistakes - 1));
            int c = (int)(Math.random() * ( numbers + mistakes - 1));
            CreateFile(path, names.get(numbers + i),names.get(p), names.get(c));
        }
        return path;
    }
}
