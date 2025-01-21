package ru.nikishechkin.yandex_algorithms.lesson_3.g_square;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * G. Построить квадрат
 * <p>
 * Задано множество, состоящее из N различных точек на плоскости. Координаты всех точек — целые числа.
 * Определите, какое минимальное количество точек нужно добавить во множество, чтобы нашлось четыре точки,
 * лежащие в вершинах квадрата.
 * <p>
 * Формат ввода
 * В первой строке вводится число N (1 ≤ N ≤ 2000) — количество точек.
 * <p>
 * В следующих N строках вводится по два числа x_i, y_i (-108 ≤ x_i, y_i ≤ 108) — координаты точек.
 * <p>
 * Формат вывода
 * В первой строке выведите число K — минимальное количество точек, которые нужно добавить во множество.
 * <p>
 * В следующих K строках выведите координаты добавленных точек x_i, y_i через пробел.
 * Координаты должны быть целыми и не превышать 10^9 по модулю.
 * <p>
 * Если решений несколько — выведите любое из них.
 */
public class Square {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputG_6.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int x, y;

            HashMap<Integer, HashSet<Integer>> x_y = new HashMap<>();
            HashMap<Integer, HashSet<Integer>> y_x = new HashMap<>();

            for (int i = 0; i < n; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();

                // Заполнение хэш-мапы x_y
                if (!x_y.containsKey(x)) {
                    HashSet<Integer> y_set = new HashSet<>();
                    x_y.put(x, y_set);
                }
                x_y.get(x).add(y);

                // Заполнение хэш-мапы y_x
                if (!y_x.containsKey(y)) {
                    HashSet<Integer> x_set = new HashSet<>();
                    y_x.put(y, x_set);
                }
                y_x.get(y).add(x);
            }

            // Количество недостаюших вершин квадрата
            int minCount = 3;

            int count = 3;
            // Недостающие вершины квадрата
            int[] cur_x = new int[3];
            int[] cur_y = new int[3];

            // Количество недостающих вершин справа и слева от вертикали
            int countLeft = 0;
            int countRight = 0;
            int countMiddle = 0;

            // Недостающие вершины квадрата справа/слева от вертикали
            int[] ne_y1_left = new int[2];
            int[] ne_y1_right = new int[2];
            int[] ne_x1_middle = new int[2];

            for (Integer x1 : x_y.keySet()) {
                for (Integer y1 : x_y.get(x1)) {
                    // Для точки x1, y2
                    count = 3;

                    // Рассматриваем парные точки по вертикали
                    for (Integer y2 : x_y.get(x1)) {

                        countLeft = 0;
                        countRight = 0;
                        countMiddle = 0;

                        // Есть точка на одной вертикали
                        if (y2 != y1) {

                            //System.out.println("Вертикаль: " + y1 + " " + y2);

                            // Есть как минимум 2 вершины квадрата

                            // Размер стороны
                            int delta = Math.abs(y2 - y1);

                            // Поиск еще 2 вершин
                            // Ищем недостающие точки справа от вертикали
                            if (!y_x.get(y1).contains(x1 + delta)) {
                                ne_y1_right[countRight] = y1;
                                countRight++;
                            }
                            if (!y_x.get(y2).contains(x1 + delta)) {
                                ne_y1_right[countRight] = y2;
                                countRight++;
                            }

                            // Ищем недостающие точки слева от вертикали
                            if (!y_x.get(y1).contains(x1 - delta)) {
                                ne_y1_left[countLeft] = y1;
                                countLeft++;
                            }
                            if (!y_x.get(y2).contains(x1 - delta)) {
                                ne_y1_left[countLeft] = y2;
                                countLeft++;
                            }

                            int d = 0;
                            int y3 = 0;

                            if (delta % 2 == 0) {
                                d = delta / 2;
                                y3 = y1 < y2 ? y1 + d : y2 + d;

                                // Ищем недостающие точки лежащие справа и слева по центру между вертикалью
                                if (y_x.containsKey(y3)) {
                                    if (!y_x.get(y3).contains(x1 + d)) {
                                        ne_x1_middle[countMiddle] = x1 + d;
                                        countMiddle++;
                                    }
                                    if (!y_x.get(y3).contains(x1 - d)) {
                                        ne_x1_middle[countMiddle] = x1 - d;
                                        countMiddle++;
                                    }
                                } else {
                                    ne_x1_middle[0] = x1 + d;
                                    ne_x1_middle[1] = x1 - d;
                                    countMiddle = 2;
                                }

                            } else {
                                countMiddle = 3;
                            }

                            if (countRight >= countLeft) {
                                count = countLeft;
                                for (int l = 0; l < countLeft; l++) {
                                    cur_x[l] = x1 - delta; // Недостающий X
                                    cur_y[l] = ne_y1_left[l]; // Недостающий Y
                                }
                            } else {
                                count = countRight;
                                for (int l = 0; l < countRight; l++) {
                                    cur_x[l] = x1 + delta; // Недостающий X
                                    cur_y[l] = ne_y1_right[l]; // Недостающий Y
                                }
                            }

                            if (countMiddle < count) {
                                count = countMiddle;
                                for (int l = 0; l < countMiddle; l++) {
                                    cur_x[l] = ne_x1_middle[l]; // Недостающий X
                                    cur_y[l] = y3; // Недостающий Y
                                }
                            }
                        }

                        //System.out.println(count);

                        if (count < minCount) {
                            minCount = count;
                        }
                    }
                }
            }

            if (minCount < 3) {
                System.out.println(minCount);
                for (int i = 0; i < minCount; i++) {
                    System.out.println(cur_x[i] + " " + cur_y[i]);
                }
            }
            // Граничное условие - если не нашлось ни одной вертикальной стороны, определить, есть ли две точки,
            // которые могут образовать квадрат?
            else {

                for (Integer x1 : x_y.keySet()) {
                    for (Integer y1 : x_y.get(x1)) {

                        // Для каждой другой точки рассматриваем варианты формирования квадрата
                        for (Integer x2 : x_y.keySet()) {
                            for (Integer y2 : x_y.get(x2)) {

                                // Если одна и та же точка, то переходим к следующей
                                if (x2 == x1 && y2 == y1) continue;

                                // Если 2 точки лежат на горизонтали
                                if ((x2 != x1 && y2 == y1)) {
                                    // 2 точки
                                    minCount = 2;
                                    int delta = x2 - x1;
                                    cur_x[0] = x1;
                                    cur_y[0] = y1 + delta;
                                    cur_x[1] = x2;
                                    cur_y[1] = y2 + delta;

                                    System.out.println(minCount);
                                    for (int i = 0; i < minCount; i++) {
                                        System.out.println(cur_x[i] + " " + cur_y[i]);
                                    }
                                    return;
                                }

                                // Если расстояние до второй точки одинаковое по X и по Y, то точка нашлась
                                if ((x2 != x1 && y2 != y1) && (Math.abs(x2 - x1) == Math.abs(y2 - y1))) {
                                    // 2 точки
                                    minCount = 2;

                                    cur_x[0] = x2;
                                    cur_y[0] = y1;
                                    cur_x[1] = x1;
                                    cur_y[1] = y2;

                                    System.out.println(minCount);
                                    for (int i = 0; i < minCount; i++) {
                                        System.out.println(cur_x[i] + " " + cur_y[i]);
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
