package ru.nikishechkin.yandex_algorithms.lesson_3.c_delete_numbers;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * C. Удаление чисел
 * <p>
 * Дан массив a из n чисел. Найдите минимальное количество чисел, после удаления которых попарная разность
 * оставшихся чисел по модулю не будет превышать 1, то есть после удаления ни одно число не должно отличаться
 * от какого-либо другого более чем на 1.
 * <p>
 * Формат ввода
 * Первая строка содержит одно целое число n (1 ≤ n ≤ 2⋅105) — количество элементов массива a.
 * Вторая строка содержит n целых чисел a1,a2,…,an (0 ≤ a_i ≤ 105) — элементы массива a.
 * <p>
 * Формат вывода
 * Выведите одно число — ответ на задачу.
 */
public class DeleteNumbers_v3 {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("resources\\lesson3\\inputC_11.txt")) {

            int max = 0;
            int cur = 0;
            int curSum1 = 0;
            int curSum2 = 0;

            Scanner scanner = new Scanner(reader);
            int n = Integer.parseInt(scanner.nextLine());
            HashMap<Integer, Integer> values = new HashMap<>();

            String str = scanner.nextLine();
            String[] val = str.split(" ");

            for (int i = 0; i < n; i++) {
                cur = Integer.parseInt(val[i]);
                values.put(cur, values.getOrDefault(cur, 0) + 1);

                curSum1 = values.get(cur) + values.getOrDefault(cur + 1, 0);
                curSum2 = values.get(cur) + values.getOrDefault(cur - 1, 0);
                if (curSum1 > max)
                    max = curSum1;
                if (curSum2 > max)
                    max = curSum2;
            }

            System.out.println(n - max);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
