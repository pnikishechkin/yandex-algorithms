package ru.nikishechkin.yandex_algorithms.lesson_2.c_ropes;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * C. Петя, Маша и верёвочки
 *
 * На столе лежали две одинаковые верёвочки целой положительной длины.
 *
 * Петя разрезал одну из верёвочек на N частей, каждая из которых имеет целую положительную длину, так что на столе
 * стало N+1 верёвочек. Затем в комнату зашла Маша и взяла одну из лежащих на столе верёвочек. По длинам оставшихся
 * на столе N верёвочек определите, какую наименьшую длину может иметь верёвочка, взятая Машей.
 *
 * Формат ввода
 * Первая строка входных данных содержит одно целое число N — количество верёвочек, оставшихся на столе (2 ≤ N ≤ 1000).
 * Во второй строке содержится N целых чисел li — длины верёвочек (1 ≤ li ≤ 1000).
 *
 * Формат вывода
 * Выведите одно целое число — наименьшую длину, которую может иметь верёвочка, взятая Машей.
 */
public class Ropes {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputC_1.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int[] mas = new int[n];
            int max = 0;
            int index_max = 0;
            int sum = 0;

            for (int i = 0; i < n; i++) {
                mas[i] = scanner.nextInt();
                sum += mas[i];
                if (mas[i] > max) {
                    max = mas[i];
                    index_max = i;
                }
            }

            // System.out.println(max);

            int res = max;
            for (int i = 0; i < n; i++) {
                if (i == index_max) {
                    continue;
                }
                res -= mas[i];
            }

            if (res <= 0) {
                res = sum;
            }

            System.out.println(res);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
