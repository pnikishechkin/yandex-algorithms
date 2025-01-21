package ru.nikishechkin.yandex_algorithms.lesson_1.с_file_format;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * C. Форматирование файла
 *
 * Петя - начинающий программист. Сегодня он написал код из n строк.
 * К сожалению оказалось, что этот код трудно читать. Петя решил исправить это, добавив в различные места пробелы.
 * А точнее, для i-й строки ему нужно добавить ровно a_i пробелов.
 * Для добавления пробелов Петя выделяет строку и нажимает на одну из трёх клавиш: Space, Tab, и Backspace.
 * При нажатии на Space в строку добавляется один пробел. При нажатии на Tab в строку добавляются четыре пробела.
 * При нажатии на Backspace в строке удаляется один пробел.
 * Ему хочется узнать, какое наименьшее количество клавиш придётся нажать, чтобы добавить необходимое количество
 * пробелов в каждую строку. Помогите ему!
 *
 * Формат ввода
 * Первая строка входных данных содержит одно целое положительное число n (1 <= n <= 10^5)
 * – количество строк в файле.
 * Каждая из следующих n строк содержит одно целое неотрицательное число a_i (0 <= a_i <= 10^9)
 * – количество пробелов, которые нужно добавить в i-ю строку файла.
 *
 * Формат вывода
 * Выведите одно число – минимальное количество нажатий, чтобы добавить в каждой строке необходимое количество пробелов.
 *
 */
public class FileFormat {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson1\\inputC_1.txt")) {
            Scanner scanner = new Scanner(reader);
            int count = scanner.nextInt();
            long res = 0;

            for (int i = 0; i < count; i++) {
                res += getClicksCount(scanner.nextInt());
            }

            System.out.println(res);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static int getClicksCount(int value) {

        // Количество кликов, которые можем покрыть табуляцией
        int countClicks = value / 4;

        // Определяем оставшееся количество пробелов в строке
        value = value % 4;

        // 3 символа это Tab и 1 Backspace
        if (value == 3) {
            countClicks += 2;
        } else { // в противном случае - добавляем пробелы, т.е. оставшиеся символы
            countClicks += value;
        }

        return countClicks;
    }
}
