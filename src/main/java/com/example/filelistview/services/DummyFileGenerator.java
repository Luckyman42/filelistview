package com.example.filelistview.services;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class DummyFileGenerator {
    public void DeleteDummyFiles(String path){
         new File(path).deleteOnExit();
    }
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

    private String GetRandomString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return  buffer.toString();
    }

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
