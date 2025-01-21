package ru.nikishechkin.yandex_algorithms.lesson_1.d_chess;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * D. Слоны и ладьи
 *
 * На шахматной доске стоят слоны и ладьи, необходимо посчитать, сколько клеток не бьется ни одной из фигур.
 *
 * Шахматная доска имеет размеры 8 на 8. Ладья бьет все клетки горизонтали и вертикали, проходящих через клетку,
 * где она стоит, до первой встретившейся фигуры. Слон бьет все клетки обеих диагоналей, проходящих через клетку,
 * где он стоит, до первой встретившейся фигуры.
 *
 * Формат ввода
 * В первых восьми строках ввода описывается шахматная доска. Первые восемь символов каждой из этих строк описывают
 * состояние соответствующей горизонтали: символ B (заглавная латинская буква) означает, что в клетке стоит слон,
 * символ R — ладья, символ * — что клетка пуста. После описания горизонтали в строке могут идти пробелы, однако
 * длина каждой строки не превышает 250 символов. После описания доски в файле могут быть пустые строки.
 *
 * Формат вывода
 * Выведите количество пустых клеток, которые не бьются ни одной из фигур.
 */
public class Chess {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson1\\inputD.txt")) {

            byte[][] board = new byte[8][8];
            char symbol;

            byte[] lad_x = new byte[64];
            byte[] lad_y = new byte[64];
            int lad_count = 0;

            byte[] slon_x = new byte[64];
            byte[] slon_y = new byte[64];
            int slon_count = 0;

            Scanner scanner = new Scanner(reader);
            for (byte i = 0; i < 8; i++) {
                String line = scanner.nextLine();
                for (byte j = 0; j < 8; j++) {
                    symbol = line.charAt(j);
                    if (symbol == '*') {
                        board[i][j] = 0;
                    } else if (symbol == 'R') { // Ладья
                        board[i][j] = 8;

                        lad_x[lad_count] = j;
                        lad_y[lad_count] = i;
                        lad_count++;

                    } else if (symbol == 'B') { // Слон
                        board[i][j] = 7;

                        slon_x[slon_count] = j;
                        slon_y[slon_count] = i;
                        slon_count++;
                    }
                }
            }

            // Закрашиваем поля, которые бьют ладьи
            for (int i = 0; i < lad_count; i++) {
                // Влево от ладьи
                int k = lad_x[i] - 1;
                while (k >= 0 && (board[lad_y[i]][k] == 0 || board[lad_y[i]][k] == 1)) {
                    board[lad_y[i]][k] = 1;
                    k--;
                }
                // Вправо от ладьи
                k = lad_x[i] + 1;
                while (k < 8 && (board[lad_y[i]][k] == 0 || board[lad_y[i]][k] == 1)) {
                    board[lad_y[i]][k] = 1;
                    k++;
                }
                // Вверх от ладьи
                k = lad_y[i] - 1;
                while (k >= 0 && (board[k][lad_x[i]] == 0 || board[k][lad_x[i]] == 1)) {
                    board[k][lad_x[i]] = 1;
                    k--;
                }
                // Вниз от ладьи
                k = lad_y[i] + 1;
                while (k < 8 && (board[k][lad_x[i]] == 0 || board[k][lad_x[i]] == 1)) {
                    board[k][lad_x[i]] = 1;
                    k++;
                }
            }

            // Закрашиваем поля, которые бьют слоны
            for (int i = 0; i < slon_count; i++) {
                // Влево-вверх
                int k = slon_x[i] - 1;
                int l = slon_y[i] - 1;
                while (k >= 0 && l >= 0 && (board[l][k] == 0 || board[l][k] == 1)) {
                    board[l][k] = 1;
                    k--;
                    l--;
                }
                // Вправо-вниз
                k = slon_x[i] + 1;
                l = slon_y[i] + 1;
                while (k < 8 && l < 8 && (board[l][k] == 0 || board[l][k] == 1)) {
                    board[l][k] = 1;
                    k++;
                    l++;
                }
                // Влево-вниз
                k = slon_x[i] - 1;
                l = slon_y[i] + 1;
                while (k >= 0 && l < 8 && (board[l][k] == 0 || board[l][k] == 1)) {
                    board[l][k] = 1;
                    k--;
                    l++;
                }
                // Вправо-вверх
                k = slon_x[i] + 1;
                l = slon_y[i] - 1;
                while (k < 8 && l >= 0 && (board[l][k] == 0 || board[l][k] == 1)) {
                    board[l][k] = 1;
                    k++;
                    l--;
                }
            }

            int res = 0;

            // Подсчет небитых клеток
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //System.out.print(board[i][j]);
                    if (board[i][j] == 0) {
                        res++;
                    }
                }
                //System.out.println();
            }
            System.out.println(res);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
