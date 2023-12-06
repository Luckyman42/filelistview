package com.example.filelistview.services;

import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ListViewService {

    public String GenerateDummyFiles(Integer numbers, Integer mistakes){
        String path = "";
        //todo implement this
        return path;
    }

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

    private String GetFirstFileName(Set<String> fileNames ){
        String firstFile = "";
        //todo implement this
        return firstFile;
    }

    public String GetResult(String path) {
        String pretty = "";
        Set<String> filenames = GetFileNames(path);
        String filename = GetFirstFileName(filenames);

        //todo implement this

        return pretty;

    }


}
