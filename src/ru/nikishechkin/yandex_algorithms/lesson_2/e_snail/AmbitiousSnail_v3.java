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
public class AmbitiousSnail_v3 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputE_8.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();

            int[][] mas = new int[2][n];
            int[] index = new int[n];
            int max_height = 0;
            int max_pos = 0;
            int i_max_pos = -1;

            int max_neg = 0;
            int i_max_neg = -1;
            int delta = 0;

            for (int i = 0; i < n; i++) {
                mas[0][i] = scanner.nextInt();
                mas[1][i] = scanner.nextInt();
                index[i] = i + 1;

                delta = mas[0][i] - mas[1][i];

                if (delta > 0) {
                    if (i_max_pos == -1) {
                        i_max_pos = i;
                        max_pos = mas[0][i];
                    } else if (mas[0][i] > max_pos) {
                        i_max_pos = i;
                        max_pos = mas[0][i];
                    } else if (mas[0][i] == max_pos &&
                            (mas[0][i] - mas[1][i]) < (mas[0][i_max_pos] - mas[1][i_max_pos])) {
                        i_max_pos = i;
                        max_pos = mas[0][i];
                    }
                }

                if (delta <= 0) {
                    if (i_max_neg == -1) {
                        i_max_neg = i;
                        max_neg = mas[0][i];
                    } else if (mas[0][i] >= max_neg) {
                        max_neg = mas[0][i];
                        i_max_neg = i;
                    }
                }
            }

            // System.out.println(max_pos + " " + i_max_pos);
            System.out.println(max_neg + " " + i_max_neg);

            int temp = 0;
            int index_positive = 0;
            int index_negative = n - 1;

            // Положительные разницы
            // Максимальное a с положительной разницей
            // Максимальное a c отрицательной разницей
            // Негативные разницы
            for (int i = 0; i < n; i++) {
                if (i == i_max_pos || i == i_max_neg)
                    continue;

                // Положительные разницы пишем вначале
                if (mas[0][i] - mas[1][i] > 0) {
                    index[index_positive] = i;
                    index_positive++;
                    max_height += (mas[0][i] - mas[1][i]);
                } else { // Отрицательные разницы кладем начиная с конца
                    index[index_negative] = i;
                    index_negative--;
                }
            }
            index[index_positive] = i_max_pos;
            max_height += max_pos;

            if (i_max_neg != -1) {
                if (mas[0][i_max_neg] > mas[1][i_max_pos]) {
                    max_height += mas[0][i_max_neg] - mas[1][i_max_pos];
                }
            }

            max_height = mas[0][0];
            int cur_height = 0;

            for (int i = 0; i < n; i++) {
                cur_height += mas[0][index[i]];
                if (cur_height > max_height) {
                    max_height = cur_height;
                }

                cur_height -= mas[1][index[i]];
                if (cur_height > max_height) {
                    max_height = cur_height;
                }
            }

            System.out.println(max_height);

            for (int i = 0; i < n; i++) {
                System.out.print((index[i] + 1) + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
