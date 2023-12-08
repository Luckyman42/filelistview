package com.example.filelistview.services;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

import java.io.FileReader;
import java.nio.file.Path;

import java.util.Set;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * This Class is specified for generating Linear Tree View for a folder where the files are:
 * <p>
 * P:parent.txt<br>
 * C:children.txt
 * </p>
 *  If every file needs to be this, then the first is where the P: is empty and the last will be where the C: is empty
 */
@Service
public class ListViewService {

    /**
     * When making formatted string, need to define where end the valid tree, and where start the mistaken list
     * This String is the separator between these two.
     */
    private final String NormalMistakeSeparator = "Mistaken files: \n";

    private Set<String> GetFileNames(String path)  {
        try (Stream<Path> stream = Files.list(Paths.get(path))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }catch (IOException e) {
            System.out.println("[GetFileNames] Something unexpected happened!" );
            System.out.println(e.getMessage());
            System.out.println(e);
            e.printStackTrace();
            return Collections.<String>emptySet();
        }
    }


    /**
     * This Method get the list of strings
     * @param path The path for the folder where I can find the files.
     * @return The list of the filenames which is in the specified folder.
     * @since 1.0
     */
    private String GetFirstFileName(String path, Set<String> fileNames ){
        for (String fileName : fileNames){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
                String line = reader.readLine();
                if(line.split("P:").length == 0){
                    reader.close();
                    return fileName;
                }
            } catch (IOException e) {
                System.out.println("[GetFirstFileName] can't read a file | " + path + fileName +" | " );
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * This Method creates a specified lines for the Linear Tree view
     * @param lineNum How many tab need the before the '->'.
     * @param filename What will appear after the -'>'.
     * @return (lineNum * '\t') + '->' + filename.
     * @since 1.0
     */
    private String GeneratePrettyLine(int lineNum, String filename){
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(lineNum));
        sb.append("->");
        sb.append(filename);
        sb.append("\n");
        return sb.toString();
    }

    /**
     * If a File has a Children, then it give it back
     * @param path The file parent folder location
     * @param fileName the name of the current file
     * @return Return the next file name if exists, if not then return null
     * @since 1.0
     */
    private String GetNextFile(String path, String fileName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
            reader.readLine();
            String[] childLine = reader.readLine().split("C:");
            if( childLine.length == 2){
                reader.close();
                return childLine[1];
            }
        } catch (IOException e) {
            System.out.println("[GetFirstFileName] can't read a file | " + path + fileName +" | " );
            System.out.println(e.getMessage());
        }

        return  null;
    }

    /**
     * Make a pretty Linear Tree View for specific files in a folder
     * @param path The location of the folder
     * @return a formatted string.
     * @since 1.0
     */
    public String GetResult(String path) {
        System.out.println("Get result is started!");
        System.out.print("The path is: ");
        System.out.println(path);
        StringBuilder prettyBuilder = new StringBuilder();
        Set<String> filenames = GetFileNames(path);
        System.out.println("Get the filenames");
        System.out.println(filenames);

        String filename = GetFirstFileName(path, filenames);
        System.out.print("The first file is: ");
        System.out.println(filename);

        int fileNum = 0;
        while(filename != null){
            filenames.remove(filename);
            prettyBuilder.append(GeneratePrettyLine(fileNum++,filename));
            filename = GetNextFile(path,filename);
        }
        prettyBuilder.append(NormalMistakeSeparator);
        for (String f : filenames){
            prettyBuilder.append(f);
            prettyBuilder.append("\n");
        }

        String result =  prettyBuilder.toString();
        System.out.println("The result is:");
        System.out.println(result);

        return result;

    }


}
