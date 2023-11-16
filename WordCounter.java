package com.example.et.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordCounter {
    public static void main(String[] args) {

        Path pathToFile = Paths.get("src/main/java/example.txt");

        printCount(readFileIntoList(pathToFile));

    }

    private static List<String> readFileIntoList(Path inputPath) {
        try {

            return Files.readAllLines(inputPath)
                    .stream()
                    .map(line -> line.trim().split("\\s*[\\s*,.?!:;]"))
                    .flatMap(Arrays::stream)
                    .collect(Collectors.toList());

        }

        catch (IOException e) {
            e.printStackTrace();

        }

        return Collections.emptyList();
    }

    private static void printCount(List<String> itemList) {

        Map<String, Long> wordsCount = itemList.stream()
                .filter(t -> t.length() > 0)
                .collect(Collectors.groupingBy(v -> v.trim(), Collectors.counting()));

        Map<String, Long> wordsSorted = wordsCount.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));

        wordsSorted.forEach((key, value) -> System.out.println(key + ": " + value));

    }

}
