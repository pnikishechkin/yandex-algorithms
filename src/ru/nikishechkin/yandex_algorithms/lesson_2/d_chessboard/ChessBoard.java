package ru.nikishechkin.yandex_algorithms.lesson_2.d_chessboard;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * D. Шахматная доска
 * <p>
 * Из шахматной доски по границам клеток выпилили связную (не распадающуюся на части) фигуру без дыр.
 * Требуется определить ее периметр.
 * <p>
 * Формат ввода
 * Сначала вводится число N (1 ≤ N ≤ 64) – количество выпиленных клеток. В следующих N строках вводятся координаты
 * выпиленных клеток, разделенные пробелом (номер строки и столбца – числа от 1 до 8).
 * Каждая выпиленная клетка указывается один раз.
 * <p>
 * Формат вывода
 * Выведите одно число – периметр выпиленной фигуры (сторона клетки равна единице).
 */
public class ChessBoard {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputD.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int[][] mas = new int[2][n];
            for (int i = 0; i < n; i++) {
                mas[0][i] = scanner.nextInt();
                mas[1][i] = scanner.nextInt();
            }

            int res = 0;
            int temp = 0;

            // Каждая ячейка может формировать периметр (а может и нет)
            // Если сторона не граничит ни с какой другой, она составляет периметр 4
            // Если сторона ячейки граничит с другим элементом, то эта сторона не идет в счет периметра общей фигуры (-1)
            // Изначально принимаем, что ячейка не граничит ни с какой другой стороной, ищем граничащие и уменьшаем
            // составляемый ей периметр
            for (int i = 0; i < n; i++) {
                temp = 4;
                for (int j = 0; j < n; j++) {
                    // Если какая-либо из координат другой ячейки совпадает с координатой выбранной, значит граничит
                    if (i != j &&
                            (
                                    (mas[0][j] == mas[0][i] && Math.abs(mas[1][j] - mas[1][i]) == 1) ||
                                            (mas[1][j] == mas[1][i] && Math.abs(mas[0][j] - mas[0][i]) == 1)
                            )) {
                        temp--;
                    }
                }
                // System.out.print(temp + " ");
                res += temp;
            }
            System.out.println(res);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
