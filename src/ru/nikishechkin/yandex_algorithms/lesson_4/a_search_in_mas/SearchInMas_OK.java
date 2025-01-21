package ru.nikishechkin.yandex_algorithms.lesson_4.a_search_in_mas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A. Быстрый поиск в массиве
 * <p>
 * Дан массив из N целых чисел. Все числа от −109 до 109.
 * Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения от L до R?”.
 * <p>
 * Формат ввода
 * Число N (1≤N≤10^5). Далее N целых чисел.
 * Затем число запросов K (1 ≤ K ≤ 10^5).
 * Далее K пар чисел L,R (−10^9 ≤ L ≤ R ≤ 10^9) — собственно запросы.
 * <p>
 * Формат вывода
 * Выведите K чисел — ответы на запросы.
 */
public class SearchInMas_OK {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson4\\inputA_3.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            scanner.nextLine();
            String[] str = scanner.nextLine().split(" ");

            int[] in = new int[str.length];
            int val = 0;

            for (int i = 0; i < str.length; i++) {
                val = Integer.parseInt(str[i]);
                //input.add(val);
                in[i] = val;
            }

            Arrays.sort(in);
//            System.out.println(Arrays.toString(in));

            int k = scanner.nextInt();
            int l = 0;
            int r = 0;
            int i1, i2, res;

            for (int i = 0; i < k; i++) {
                l = scanner.nextInt();
                r = scanner.nextInt();
                i1 = getFirstIndex(l, in);
                i2 = getLastIndex(r, in);
                res = i2 - i1 + 1;
                if (res < 0) res = 0;

//                System.out.print(l + " " + r + " | " + i1 + " " + i2 + " | " + res + " ");
//                System.out.println();
                System.out.print(res + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int getFirstIndex(int l, int[] mas) {
        int mid = mas.length / 2;
        int start = 0;
        int last = mas.length - 1;
        int res = 0;
        boolean found = false;

        while (start <= last) {
            if (l > mas[mid]) {
                start = mid + 1;
            } else {
                last = mid - 1;
            }

            if (mas[mid] == l) {
                found = true;
                res = mid;
            }

            mid = (start + last) / 2;
        }

        if (found) {
            // Элемент найден
            return res;
        } else {
            // Элемент не найден, возвращаем следующий по счету элемент
            // System.out.println("mid = " + mid);
            if (mas[mid] < l) mid++;
            return mid;
        }
    }

    public static int getLastIndex(int l, int[] mas) {
        int mid = mas.length / 2;
        int start = 0;
        int last = mas.length - 1;
        int res = 0;
        boolean found = false;

        while (start <= last) {
            if (l >= mas[mid]) {
                start = mid + 1;
            } else {
                last = mid - 1;
            }

            if (mas[mid] == l) {
                found = true;
                res = mid;
            }

            mid = (start + last) / 2;
        }

        if (found) {
            // Элемент найден
            return res;
        } else {
            // Элемент не найден, возвращаем предыдущий элемент
            // System.out.println("mid = " + mid);
            if (mas[mid] > l) mid--;
            return mid;
        }
    }
}
