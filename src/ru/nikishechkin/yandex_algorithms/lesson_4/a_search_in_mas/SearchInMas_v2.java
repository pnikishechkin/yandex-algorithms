package ru.nikishechkin.yandex_algorithms.lesson_4.a_search_in_mas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * A. Быстрый поиск в массиве
 * <p>
 * Дан массив из N целых чисел. Все числа от −109 до 109.
 * Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения отL доR?”.
 * <p>
 * Формат ввода
 * Число N (1≤N≤10^5). Далее N целых чисел.
 * Затем число запросов K (1 ≤ K ≤ 10^5).
 * Далее K пар чисел L,R (−10^9 ≤ L ≤ R ≤ 10^9) — собственно запросы.
 * <p>
 * Формат вывода
 * Выведите K чисел — ответы на запросы.
 */
public class SearchInMas_v2 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson4\\inputA_2.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            scanner.nextLine();
            String[] str = scanner.nextLine().split(" ");
            TreeMap<Integer, Integer> inp = new TreeMap<>();
            int val = 0;

            for (int i = 0; i < str.length; i++) {
                val = Integer.parseInt(str[i]);
                inp.put(val, inp.getOrDefault(val, 0) + 1);
            }

            int k = scanner.nextInt();
            int l = 0;
            int r = 0;
            int i1, i2, res;

            for (int i = 0; i < k; i++) {
                l = scanner.nextInt();
                r = scanner.nextInt();
//                i1 = getFirstIndex(l, in1);
//                i2 = n - getLastIndex(r, in2) - 1;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
