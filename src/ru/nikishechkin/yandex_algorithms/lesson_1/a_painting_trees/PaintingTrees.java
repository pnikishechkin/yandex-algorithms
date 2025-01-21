package ru.nikishechkin.yandex_algorithms.lesson_1.a_painting_trees;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A. Покраска деревьев
 *
 * Вася и Маша участвуют в субботнике и красят стволы деревьев в белый цвет.
 * Деревья растут вдоль улицы через равные промежутки в 1 метр.
 * Одно из деревьев обозначено числом ноль, деревья по одну сторону занумерованы положительными числами  1, 2 и т.д.,
 * а в другую — отрицательными −1, −2 и т.д.
 * Ведро с краской для Васи установили возле дерева P, а для Маши — возле дерева Q.
 *
 * Ведра с краской очень тяжелые и Вася с Машей не могут их переставить, поэтому они окунают кисть в ведро и
 * уже с этой кистью идут красить дерево. Краска на кисти из ведра Васи засыхает, когда он удаляется от ведра
 * более чем на V метров, а из ведра Маши — на M метров. Определите, сколько деревьев может быть покрашено.
 *
 * Формат ввода
 * В первой строке содержится два целых числа: P и V — номер дерева, у которого стоит ведро Васи и на сколько
 * деревьев он может от него удаляться.
 * В второй строке содержится два целых числа Q и M — аналогичные данные для Маши.
 * Все числа целые и по модулю не превосходят 10^8
 *
 * Формат вывода
 * Выведите одно число — количество деревьев, которые могут быть покрашены.
 */
public class PaintingTrees {
    public static void main(String[] args) {

        try (FileReader reader = new FileReader("resources\\lesson1\\inputA.txt")) {
            Scanner scanner = new Scanner(reader);

            int p = Integer.parseInt(scanner.next());
            int v = Integer.parseInt(scanner.next());
            int q = Integer.parseInt(scanner.next());
            int m = Integer.parseInt(scanner.next());

            // Определяем границы множеств
            int l1 = p - v;
            int r1 = p + v;

            int l2 = q - m;
            int r2 = q + m;

            int res = 0;

            // Если множества не пересекаются, берем сумму множеств
            if (l2 > r1 || l1 > r2) {
                res = (r1 - l1 + 1) + (r2 - l2 + 1);
            } else { // Множества пересекаются
                if (l2 < l1) {
                    l1 = l2;
                }
                if (r2 > r1) {
                    r1 = r2;
                }
                res = r1 - l1 + 1;
            }
            System.out.println(res);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
