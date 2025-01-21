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
public class AmbitiousSnail_v5 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputE_16.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();

            int[][] mas = new int[2][n];
            int[] index_1 = new int[n];
            int[] index_2 = new int[n];
            long max_height_1 = 0;
            long max_height_2 = 0;

            long max_neg = 0;
            int i_max_neg = -1;
            long delta = 0;
            int count_pos = 0;
            int count_neg = 0;
            long cur_height1 = 0;
            long cur_height2 = 0;

            int[] indexPos = new int[n];
            int[] indexPos2 = new int[n];
            int[] indexNeg = new int[n];

            for (int i = 0; i < n; i++) {
                mas[0][i] = scanner.nextInt();
                mas[1][i] = scanner.nextInt();
                index_1[i] = i;

                delta = mas[0][i] - mas[1][i];

                if (delta < 0) {
                    if (i_max_neg == -1) {
                        i_max_neg = i;
                        max_neg = mas[0][i];
                    } else if (mas[0][i] >= max_neg) {
                        max_neg = mas[0][i];
                        i_max_neg = i;
                    }

                    // Завпоминаем индексы с отрицательной разницей
                    indexNeg[count_neg] = i;
                    count_neg++;

                } else {
                    // Запоминаем индексы с положительной разницей
                    indexPos[count_pos] = i;
                    indexPos2[count_pos] = i;
                    count_pos++;
                }
            }

            int temp;

            // Сортировка двумя способами
            // Положительные в отсортированном порядке по delta
            // Максимальный a_i для негативной разницы
            // Негативные разницы
            for (int i = 0; i < count_pos; i++) {
                for (int j = 0; j < count_pos; j++) {

                    // 1 вариант сортировки - по дельтам
                    int delta1 = mas[0][indexPos[j]] - mas[1][indexPos[j]];
                    int delta2 = mas[0][indexPos[i]] - mas[1][indexPos[i]];
                    if (delta1 < delta2) {
                        temp = indexPos[j];
                        indexPos[j] = indexPos[i];
                        indexPos[i] = temp;
                    }

                    // 2 вариант сортировки по b_i
                    if (mas[1][indexPos2[j]] > mas[1][indexPos2[i]]) {
                        temp = indexPos2[j];
                        indexPos2[j] = indexPos2[i];
                        indexPos2[i] = temp;
                    }

                }
            }

            /*
            System.out.println(count_pos + " | " + Arrays.toString(indexPos));
            System.out.println(count_neg + " | " + Arrays.toString(indexNeg));
            System.out.println(i_max_neg + " | " + max_neg);
             */

            // Собираем правильную последовательность индексов
            for (int i = 0; i < count_pos; i++) {
                index_1[i] = indexPos[i];
                index_2[i] = indexPos2[i];
            }
            if (i_max_neg != -1) {
                index_1[count_pos] = i_max_neg;
                index_2[count_pos] = i_max_neg;
            }
            int l = count_pos + 1;
            for (int i = 0; i < count_neg; i++) {
                if (indexNeg[i] != i_max_neg) {
                    index_1[l] = indexNeg[i];
                    index_2[l] = indexNeg[i];
                    l++;
                }
            }

            max_height_1 = mas[0][index_1[0]];
            max_height_2 = mas[0][index_2[0]];

            for (int i = 0; i < count_pos + 1; i++) {

                //System.out.println(index[i] + " " + (cur_height + mas[0][index[i]]) + " " + (cur_height + mas[0][index[i]] - mas[0][index[i]]));

                // Сборка по 1 варианту
                if (i_max_neg == -1 && i == count_pos) continue;
                cur_height1 += mas[0][index_1[i]];
                if (cur_height1 > max_height_1) {
                    max_height_1 = cur_height1;
                }
                cur_height1 -= mas[1][index_1[i]];

                // Сборка по 2 варианту
                if (i_max_neg == -1 && i == count_pos) continue;
                cur_height2 += mas[0][index_2[i]];
                if (cur_height2 > max_height_2) {
                    max_height_2 = cur_height2;
                }
                cur_height2 -= mas[1][index_2[i]];
            }

            //////////////////////////////////////////////////////////////////////////////////////////////////////////

            // System.out.println(Arrays.toString(indexPos));

            // 2 вариант сортировки
            // Положительные разницы в отсортированном порядке по b_i
            // Максимальный a_i для негативной разницы
            // Негативные разницы

            // System.out.println(Arrays.toString(indexPos));

            // System.out.println(Arrays.toString(index_2));


            // Вывод результата

            if (max_height_1 > max_height_2) {
                System.out.println(max_height_1);
                for (int i = 0; i < n; i++) {
                    System.out.print((index_1[i] + 1) + " ");
                }
            } else {
                System.out.println(max_height_2);
                for (int i = 0; i < n; i++) {
                    System.out.print((index_2[i] + 1) + " ");
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
