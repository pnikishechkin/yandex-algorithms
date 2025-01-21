package ru.nikishechkin.yandex_algorithms.lesson_2.a_min_rectangle;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A. Минимальный прямоугольник
 *
 * На клетчатой плоскости закрашено K клеток.
 * Требуется найти минимальный по площади прямоугольник, со сторонами, параллельными линиям сетки,
 * покрывающий все закрашенные клетки.
 *
 * Формат ввода
 * Во входном файле, на первой строке, находится число K (1 ≤ K ≤ 100). На следующих K строках находятся
 * пары чисел Xi и Yi — координаты закрашенных клеток (|Xi|, |Yi| ≤ 109).
 *
 * Формат вывода
 * Выведите в выходной файл координаты левого нижнего и правого верхнего углов прямоугольника.
 */
public class MinRectangle {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputA.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int x, y, minX = 0, maxX = 0, minY = 0, maxY = 0;

            for (int i = 0; i < n; i++) {
                x = scanner.nextInt();
                y = scanner.nextInt();

                if (i == 0) {
                    minX = x;
                    maxX = x;
                    minY = y;
                    maxY = y;
                } else {
                    if (x < minX) minX = x;
                    if (x > maxX) maxX = x;
                    if (y < minY) minY = y;
                    if (y > maxY) maxY = y;
                }
            }

            System.out.println(minX + " " + minY + " " + maxX + " " + maxY);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
