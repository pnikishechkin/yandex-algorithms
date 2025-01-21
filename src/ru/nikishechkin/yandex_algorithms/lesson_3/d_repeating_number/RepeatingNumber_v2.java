package ru.nikishechkin.yandex_algorithms.lesson_3.d_repeating_number;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * D. Повторяющееся число
 *
 * Вам дана последовательность измерений некоторой величины. Требуется определить, повторялась ли какое-либо число,
 * причём расстояние между повторами не превосходило k.
 *
 * Формат ввода
 * В первой строке задаются два числа n и k (1 ≤ n, k ≤ 105).
 * Во второй строке задаются n чисел, по модулю не превосходящих 109.
 *
 * Формат вывода
 * Выведите YES, если найдется повторяющееся число и расстояние между повторами не превосходит k
 * и NO в противном случае.
 */
public class RepeatingNumber_v2 {
    public static void main(String[] args) {

        //long a = System.currentTimeMillis();
        try (FileReader reader = new FileReader("resources\\lesson3\\inputD_1.txt")) {
            Scanner scanner = new Scanner(reader);
            String[] str = scanner.nextLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);
            int val = 0;
            boolean res = false;

            Set<Integer> list = new LinkedHashSet<>();
            str = scanner.nextLine().split(" ");

            for (int i = 0; i < n; i++) {
                if (list.size() > k) {
                    list.remove(list.iterator().next());
                }
                val = Integer.parseInt(str[i]);

                if (list.contains(val)) {
                    res = true;
                    break;
                }
                list.add(val);
                System.out.println(list);
            }

            if (res) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //long b = System.currentTimeMillis();
        //System.out.println(b - a);
    }
}
