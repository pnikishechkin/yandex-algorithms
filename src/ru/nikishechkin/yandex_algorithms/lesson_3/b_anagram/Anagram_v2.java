package ru.nikishechkin.yandex_algorithms.lesson_3.b_anagram;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * B. Анаграмма?
 *
 * Задано две строки, нужно проверить, является ли одна анаграммой другой. Анаграммой называется строка,
 * полученная из другой перестановкой букв.
 *
 * Формат ввода
 * Строки состоят из строчных латинских букв, их длина не превосходит 100000. Каждая записана в отдельной строке.
 *
 * Формат вывода
 * Выведите "YES" если одна из строк является анаграммой другой и "NO" в противном случае.
 */
public class Anagram_v2 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputB.txt")) {
            Scanner scanner = new Scanner(reader);
            final String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            //System.out.println(s1);
            //System.out.println(s2);

            if (s1.length() != s2.length()) {
                System.out.println("NO");
                return;
            }

            HashMap<Character, Integer> dict = new HashMap<>();
            for(char c : s1.toCharArray()) {
                if (!dict.containsKey(c)) {
                    dict.put(c, 1);
                } else {
                    dict.put(c, dict.get(c) + 1);
                }
            }

            for(char c : s2.toCharArray()) {
                if (!dict.containsKey(c) || dict.get(c) == 0) {
                    System.out.println("NO");
                    return;
                } else {
                    dict.put(c, dict.get(c) - 1);
                }
            }

            System.out.println("YES");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
