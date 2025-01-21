package ru.nikishechkin.yandex_algorithms.lesson_3.g_square;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
public class Square_v2 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputG_2.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int x, y;

            TreeMap<Integer, TreeSet<Integer>> x_y = new TreeMap<>();
            TreeMap<Integer, TreeSet<Integer>> y_x = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();

                // Заполнение хэш-мапы x_y
                if (!x_y.containsKey(x)) {
                    TreeSet<Integer> y_set = new TreeSet<>();
                    x_y.put(x, y_set);
                }
                x_y.get(x).add(y);

                // Заполнение хэш-мапы y_x
                if (!y_x.containsKey(y)) {
                    TreeSet<Integer> x_set = new TreeSet<>();
                    y_x.put(y, x_set);
                }
                y_x.get(y).add(x);
            }

            int minCount = 3;
            int count = 0;
            int delta = 0;
            int x3, y3, x4, y4;

            for (Integer x1: x_y.keySet()) {
                for (Integer y1: x_y.get(x1)) {
                    // Для каждой другой точки рассматриваем варианты формирования квадрата
                    for (Integer x2: x_y.keySet()) {
                        for (Integer y2: x_y.get(x1)) {

                            // Если одна и та же точка, то переходим к следующей
                            if (x2 == x1 && y2 == y1) continue;

                            // Если две точки не лежат на одной линии и имеют разные длины сторон между ними (в прицнипе
                            // не могут организовать квадрат), переходим к следующей
                            if ((x2 != x1 || y2 != y1) && x2 - x1 != y2 - y1) continue;

                            // Для каждой другой точки рассматриваем варианты формирования квадрата

                            count = 3;

                            if (x2 == x1) { // Точки лежат на вертикальной линии
                                count--;
                                delta = y1 - y2;
                                x3 = x2 + delta;

                                if (x_y.containsKey(x3) && x_y.get(x3).contains(y2)) {
                                    count--;
                                }
                                if (x_y.containsKey(x3) && x_y.get(x3).contains(y1)) {
                                    count--;
                                }
                            } else if (y2 == y1) { // Точки лежат на горизонтальной линии
                                count--;
                                delta = Math.abs(x1 - x2);
                                int new_x = x2 + delta;

                                if (x_y.containsKey(new_x) && x_y.get(new_x).contains(y2)) {
                                    count--;
                                }
                                if (x_y.containsKey(new_x) && x_y.get(new_x).contains(y1)) {
                                    count--;
                                }
                            }
                        }
                    }

                }
            }

            if (minCount == 3 && x_y.size() > 1)
                minCount = 2;

            System.out.println(minCount);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
