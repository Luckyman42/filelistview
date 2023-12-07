package com.example.filelistview.services;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

import java.io.FileReader;
import java.nio.file.Path;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ListViewService {

    private final String NormalMistakeSeparator = "Mistaken files: \n";

    private Set<String> GetFileNames(String path)  {
        try (Stream<Path> stream = Files.list(Paths.get(path))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }catch (IOException e) {
            return Collections.<String>emptySet();
        }
    }

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

    private String GeneratePrettyLine(int lineNum, String filename){
        StringBuilder sb = new StringBuilder();
        sb.append("\t".repeat(lineNum));
        sb.append(filename);
        sb.append("\n");
        return sb.toString();
    }

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

    public String GetResult(String path) {
        StringBuilder prettyBuilder = new StringBuilder();
        Set<String> filenames = GetFileNames(path);
        String filename = GetFirstFileName(path, filenames);
        int fileNum = 0;
        while(filename != null){
            filenames.remove(filename);
            prettyBuilder.append(GeneratePrettyLine(fileNum++,filename));
            filename = GetNextFile(path,filename);
        }
        prettyBuilder.append(NormalMistakeSeparator);
        for (String f : filenames){
            prettyBuilder.append(f);
        }

        return prettyBuilder.toString();

    }


}
