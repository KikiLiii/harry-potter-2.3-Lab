package edu.pro;

import java.io.IOException;
import java.nio.charset.StandardCharsets; // Додано
import java.nio.file.Files;
import java.nio.file.Path; // Змінено на Path
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static String cleanText(String url) throws IOException {
        // Зчитування файлу з вказаним кодуванням (наприклад, UTF-8)
        return Files.readString(Path.of(url), StandardCharsets.UTF_8)
                .replaceAll("[^A-Za-z ]", " ")
                .toLowerCase(Locale.ROOT);
    }

    public static void main(String[] args) throws IOException {
        LocalDateTime start = LocalDateTime.now();

        String content = cleanText("src/edu/pro/txt/harry.txt");
        String[] words = content.split("\\s+");

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            if (!word.isBlank()) {
                frequencyMap.merge(word, 1, Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(frequencyMap.entrySet());
        sortedWords.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

        sortedWords.stream().limit(30).forEach(entry ->
                System.out.println(entry.getKey() + " " + entry.getValue())
        );

        LocalDateTime finish = LocalDateTime.now();
        System.out.println("------");
        System.out.println(ChronoUnit.MILLIS.between(start, finish));
    }
}
