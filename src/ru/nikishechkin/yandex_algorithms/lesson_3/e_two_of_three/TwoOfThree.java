package ru.nikishechkin.yandex_algorithms.lesson_3.e_two_of_three;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * E. Два из трех
 * <p>
 * Вам даны три списка чисел. Найдите все числа, которые встречаются хотя бы в двух из трёх списков.
 * <p>
 * Формат ввода
 * Во входных данных описывается три списка чисел. Первая строка каждого описания списка состоит из длины списка
 * n (1 ≤ n ≤ 1000). Вторая строка описания содержит список натуральных чисел, записанных через пробел.
 * Числа не превосходят 10^9.
 * <p>
 * Формат вывода
 * Выведите все числа, которые содержатся хотя бы в двух списках из трёх, в порядке возрастания.
 * Обратите внимание, что каждое число необходимо выводить только один раз.
 */
public class TwoOfThree {
    public static void main(String[] args) {

//        long a = System.currentTimeMillis();

        try (FileReader reader = new FileReader("resources\\lesson3\\inputE_1.txt")) {
            Scanner scanner = new Scanner(reader);

            TreeSet<Integer> data1 = new TreeSet<>();
            TreeSet<Integer> data2 = new TreeSet<>();
            TreeSet<Integer> data3 = new TreeSet<>();
            int n = 0;

            for (int i = 0; i < 3; i++) {
                n = Integer.parseInt(scanner.nextLine());
                String[] val = scanner.nextLine().split(" ");
                TreeSet<Integer> data;
                if (i == 0)
                    data = data1;
                else if (i == 1)
                    data = data2;
                else
                    data = data3;

                for (int j = 0; j < n; j++) {
                    data.add(Integer.parseInt(val[j]));
                }
            }

            TreeSet<Integer> res = new TreeSet<>();

            // Сбор повторяющихся 1-2, 1-3
            for (Integer val : data1) {
                if (data2.contains(val)) {
                    res.add(val);
                } else if (data3.contains(val)) {
                    res.add(val);
                }
            }
            // Сбор повторяющихся 2-3
            for (Integer val : data2) {
                if (data3.contains(val)) {
                    res.add(val);
                }
            }

            // Вывод результата
            for (Integer val : res) {
                System.out.print(val + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

//        long b = System.currentTimeMillis();
//        System.out.println(b - a);
    }
}
