package ru.nikishechkin.yandex_algorithms.lesson_3.b_anagram;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
public class Anagram {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputB.txt")) {
            Scanner scanner = new Scanner(reader);
            final String s1 = scanner.nextLine();
            String s2 = scanner.nextLine();
            System.out.println(s1);
            System.out.println(s2);

            if (s1.length() != s2.length()) {
                System.out.println("NO");
                return;
            }

            List<Character> list1 = new ArrayList<Character>();
            for(char c : s1.toCharArray()) {
                list1.add(c);
            }

            List<Character> list2 = new ArrayList<Character>();
            for(char c : s2.toCharArray()) {
                list2.add(c);
            }

            list1.retainAll(list2);
            System.out.println(list1);
            System.out.println(list1.size());


            HashMap<String, Integer> dict = new HashMap<>();


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
