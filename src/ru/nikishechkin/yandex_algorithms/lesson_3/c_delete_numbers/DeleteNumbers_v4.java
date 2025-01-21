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
public class DeleteNumbers_v4 {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("resources\\lesson3\\inputC_11.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = Integer.parseInt(scanner.nextLine());
            HashMap<Integer, Integer> values = new HashMap<>();

            String str = scanner.nextLine();
            String[] val = str.split(" ");

            for (int i = 0; i < n; i++) {
                int cur = Integer.parseInt(val[i]);
                values.put(cur, values.getOrDefault(cur, 0) + 1);
            }

            int max = 0;
            int curSum = 0;

            for (Integer key : values.keySet()) {
                curSum = values.get(key) + values.getOrDefault(key + 1, 0);

                if (curSum > max)
                    max = curSum;
            }

            System.out.println(n - max);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
