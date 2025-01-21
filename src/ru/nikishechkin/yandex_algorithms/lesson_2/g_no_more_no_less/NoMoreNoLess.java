package ru.nikishechkin.yandex_algorithms.lesson_2.g_no_more_no_less;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * G. Ни больше ни меньше
 * <p>
 * Дан массив целых положительных чисел a длины n.
 * Разбейте его на минимально возможное количество отрезков, чтобы каждое число было не меньше длины отрезка
 * которому оно принадлежит. Длиной отрезка считается количество чисел в нем.
 * <p>
 * Разбиение массива на отрезки считается корректным, если каждый элемент принадлежит ровно одному отрезку.
 * <p>
 * Формат ввода
 * Первая строка содержит одно целое число t (1 ≤ t ≤ 1000) — количество наборов тестовых данных.
 * Затем следуют t наборов тестовых данных.
 * <p>
 * Первая строка набора тестовых данных содержит одно целое число n (1 ≤ n ≤ 105) — длину массива.
 * Следующая строка содержит n целых чисел a1, a2, …, an (1 ≤ ai ≤ n) — массив a.
 * Гарантируется, что сумма n по всем наборам тестовых данных не превосходит 2 ⋅ 10^5.
 * <p>
 * Формат вывода
 * Для каждого набора тестовых данных в первой строке выведите число k — количество отрезков в вашем разбиении.
 * <p>
 * Затем в следующей строке выведите k чисел len1, len2, …, lenk  — длины отрезков в порядке слева направо.
 */
public class NoMoreNoLess {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputG_2.txt")) {
            Scanner scanner = new Scanner(reader);
            int n, t = scanner.nextInt();
            int count = 0;
            int length = 0;
            int min = 0, cur = 0;
            int[] res;

            for (int i = 0; i < t; i++) {
                n = scanner.nextInt();
                count = 0;
                length = 0;
                res = new int[n];

                for (int j = 0; j < n; j++) {
                    cur = scanner.nextInt();
                    // Начало нового отрезка
                    if (length == 0) {
                        min = cur;
                        length = 1;
                    } else {
                        // НЕ МОЖЕМ ПРОДЛИТЬ ОТРЕЗОК, ДАЖЕ ТЕКУЩИМ ЗНАЧЕНИЕМ
                        // 1. Если новое число уже меньше длины накопленного отрезка, то отрезок прекращаем,
                        // а новое число включаем в следующий отрезок.
                        // 2. Если минимальное число равно длине текущего отрезка
                        if (cur <= length || min <= length) {
                            res[count] = length;

                            // Записываем текущее число в новый отрезок, и ставим длину 1
                            min = cur;
                            length = 1;

                            // Счетчик количества отрезков
                            count++;
                        } else { // МОЖЕМ ПРОДЛИТЬ ОТРЕЗОК
                            // Обновляем минимальное значение
                            if (cur < min) {
                                min = cur;
                            }
                            length++;
                        }
                    }

                    // Если последний элемент, завершаем отрезок
                    if (j == (n - 1) && length != 0) {
                        res[count] = length;
                        count++;
                    }
                }

                // Вывод результата
                System.out.println(count);
                for (int j = 0; j < count; j++) {
                    System.out.print(res[j] + " ");
                }
                System.out.println("");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
