package ru.nikishechkin.yandex_algorithms.lesson_2.e_snail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * E. Амбициозная улитка
 *
 * Домашний питомец мальчика Васи — улитка Петя. Петя обитает на бесконечном в обе стороны вертикальном столбе,
 * который для удобства можно представить как числовую прямую. Изначально Петя находится в точке 0.
 * Вася кормит Петю ягодами. У него есть n ягод, каждая в единственном экземпляре.
 * Вася знает, что если утром он даст Пете ягоду с номером i, то поев и набравшись сил, за остаток дня Петя поднимется
 * на a_i единиц вверх по столбу, но при этом за ночь, потяжелев, съедет на b_i единиц вниз.
 * Параметры различных ягод могут совпадать.
 *
 * Пете стало интересно, а как оно там, наверху, и Вася взялся ему в этом помочь. Ближайшие n дней он будет кормить
 * Петю ягодами из своего запаса таким образом, чтобы максимальная высота, на которой побывал Петя за эти n
 * дней была максимальной. К сожалению, Вася не умеет программировать, поэтому он попросил вас о помощи.
 *
 * Найдите, максимальную высоту, на которой Петя сможет побывать за эти n дней и в каком порядке Вася должен давать
 * Пете ягоды, чтобы Петя смог её достичь!
 *
 * Формат ввода
 * В первой строке входных данных дано число n (1 ≤ n ≤ 5*10^5) — количество ягод у Васи.
 * В последующих n строках описываются параметры каждой ягоды. В i + 1 строке дано два числа a_i и b_i
 * (0 ≤ a_i, b_i ≤ 10^9) — то, насколько поднимется улитка за день после того, как съест i ягоду и насколько опустится
 * за ночь.
 *
 * Формат вывода
 * В первой строке выходных данных выведите единственное число — максимальную высоту, которую сможет достичь Петя,
 * если Вася будет его кормить оптимальным образом. В следующей строке выведите n различных целых чисел от 1 до n
 * — порядок, в котором Вася должен кормить Петю (i число в строке соответствует номеру ягоды, которую Вася должен
 * дать Пете в i день, чтобы Петя смог достичь максимальной высоты).
 */
public class AmbitiousSnail_v7 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputE_16.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();

            int[][] mas = new int[2][n];

            long max_neg = 0;
            int i_max_neg = -1;
            long delta = 0;
            int count_pos = 0;
            int count_neg = 0;
            long sum_pos = 0;

            int[] indexPos = new int[n];
            int[] indexNeg = new int[n];
            int[] index = new int[n];

            int i_max_bi = -1;
            long max_bi_pos = 0;

            for (int i = 0; i < n; i++) {
                mas[0][i] = scanner.nextInt();
                mas[1][i] = scanner.nextInt();

                delta = mas[0][i] - mas[1][i];

                if (delta < 0) {
                    if (i_max_neg == -1) {
                        i_max_neg = i;
                        max_neg = mas[0][i];
                    } else if (mas[0][i] >= max_neg) {
                        max_neg = mas[0][i];
                        i_max_neg = i;
                    }

                    // Запоминаем индексы с отрицательной разницей
                    indexNeg[count_neg] = i;
                    count_neg++;

                } else {
                    // Запоминаем индексы с положительной разницей
                    indexPos[count_pos] = i;

                    // Сумму дельты запомнили
                    sum_pos += delta;

                    if (count_pos == 0) {
                        max_bi_pos = mas[1][i];
                        i_max_bi = i;
                    } else if (mas[1][i] > max_bi_pos) {
                        max_bi_pos = mas[1][i];
                        i_max_bi = i;
                    }

                    count_pos++;
                }
            }

            // Два варианта максимального значения
            long var1 = sum_pos + max_neg;
            long var2 = sum_pos + max_bi_pos;

            int j = 0;

            // Собираем правильную последовательность индексов
            // Положительные, элемент с максимальным bi, отрицательный с максимальным ai, отрицательные

            // Положительные
            for (int i = 0; i < count_pos; i++) {
                // Пропускаем значение с максимальным bi, чтобы установить его в конец положительного ряда
                if (indexPos[i] == i_max_bi) {
                    continue;
                }
                index[j] = indexPos[i];
                j++;
            }

            // Устанавливаем элемент с максимальным bi
            index[j] = i_max_bi;

            // Отрицательный с максимальным ai
            if (i_max_neg != -1) {
                index[count_pos] = i_max_neg;
            }

            // Отрицательные
            int l = count_pos + 1;
            for (int i = 0; i < count_neg; i++) {
                if (indexNeg[i] != i_max_neg) {
                    index[l] = indexNeg[i];
                    l++;
                }
            }

            // Вывод результата
            System.out.println(var1 > var2 ? var1 : var2);
            for (int i = 0; i < n; i++) {
                System.out.print((index[i] + 1) + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
