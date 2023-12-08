package com.example.filelistview.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DummyFileGeneratorTest {
    DummyFileGenerator dummyFileGenerator;

    private static final long Seed = 42;
    private static final String NotRandomCharacters = "sbrbiyhsrcxljjhrmtuzxdlltykuedpifgvjeephtqocpctuaijfvakuzcsnmwhfypveqjjdjllqlsmymovadbvulthqfiezwmmo";
    @BeforeEach
    public void InitTest(){
        dummyFileGenerator = new DummyFileGenerator(Seed);
    }

    private List<String> GetFileNames(String path)  {
        try (Stream<Path> stream = Files.list(Paths.get(path))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private List<String> GetFilesContent(String path, List<String> fileNames) {
        List<String> contents = new ArrayList<>();
        for (String fileName : fileNames) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path + fileName));
                String content = reader.readLine() +
                                "\n" +
                                reader.readLine();
                contents.add(content);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return  contents;
    }
    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:0", "0:1", "5:0", "0:5", "26:0", "0:26", "100:0", "0:100",
                        "1:1", "5:1", "1:5", "5:5", "26:26", "100:26", "100:100"}, delimiter = ':')
    void generateDummyFiles_number_of_files_Test(int n, int m) {
        String path = dummyFileGenerator.GenerateDummyFiles(n,m);
        List<String> filenames = GetFileNames(path);
        assertEquals(n+m,filenames.size());
        dummyFileGenerator.DeleteDummyFiles(path);
    }

    @ParameterizedTest
    @ValueSource(ints={1,5,10,26,100})
    void generateDummyFiles_good_content_format_Test(int n) {
        String path = dummyFileGenerator.GenerateDummyFiles(n,0);
        List<String> filenames = GetFileNames(path);
        List<String> contents = GetFilesContent(path,filenames);
        Set<String> parents = new HashSet<>();
        Set<String> children = new HashSet<>();
        for (String content : contents){
            String[] lines = content.split("\n");
            assertEquals(2,lines.length);
            assertTrue(lines[0].startsWith("P:"));
            String[] firstLine = lines[0].split("P:");
            if(firstLine.length == 2){
               parents.add(firstLine[1]);
               assertTrue(filenames.contains(firstLine[1]));
            }else if(firstLine.length == 0){
                parents.add("");
            }

            assertTrue(lines[1].startsWith("C:"));
            String[] secondLine = lines[1].split("C:");
            if(secondLine.length == 2){
                children.add(secondLine[1]);
                assertTrue(filenames.contains(secondLine[1]));
            }else if(secondLine.length == 0){
                children.add("");
            }
        }
        assertEquals(n, parents.size());
        assertEquals(n, children.size());

        dummyFileGenerator.DeleteDummyFiles(path);
    }
    @ParameterizedTest
    @ValueSource(ints={1,5,10,26,100})
    void generateDummyFiles_mistaken_content_format_Test(int n) {
        String path = dummyFileGenerator.GenerateDummyFiles(0,n);
        List<String> filenames = GetFileNames(path);
        List<String> contents = GetFilesContent(path,filenames);
        for (String content : contents){
            String[] lines = content.split("\n");
            assertEquals(2,lines.length);

            assertTrue(lines[0].startsWith("P:"));
            String[] firstLine = lines[0].split("P:");
            assertEquals(2,firstLine.length);
            assertFalse(firstLine[1].isEmpty());

            assertTrue(lines[1].startsWith("C:"));
            String[] secondLine = lines[1].split("C:");
            assertEquals(2,secondLine.length);
            assertFalse(secondLine[1].isEmpty());
        }

        dummyFileGenerator.DeleteDummyFiles(path);
    }
}