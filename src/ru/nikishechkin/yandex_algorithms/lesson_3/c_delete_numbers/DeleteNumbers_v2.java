package ru.nikishechkin.yandex_algorithms.lesson_3.c_delete_numbers;

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
public class DeleteNumbers_v2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer, Integer> values = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int cur = scanner.nextInt();
            values.put(cur, values.getOrDefault(cur, 0) + 1);
        }

        // System.out.println(values);

        int max = 0;
        int curSum = 0;

        for (Integer key : values.keySet()) {
            curSum = values.get(key) + values.getOrDefault(key + 1, 0);
            // System.out.println(curSum);
            if (curSum > max)
                max = curSum;
        }

        System.out.println(n - max);
    }
}
