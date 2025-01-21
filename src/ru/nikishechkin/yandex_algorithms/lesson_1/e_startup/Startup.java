package ru.nikishechkin.yandex_algorithms.lesson_1.e_startup;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * E. Прибыльный стартап
 * <p>
 * k друзей организовали стартап по производству укулеле для кошек. На сегодняшний день прибыль составила n
 * рублей. Вы, как главный бухгалтер компании, хотите в каждый из ближайших d дней приписывать по одной цифре
 * в конец числа, выражающего прибыль. При этом в каждый из дней прибыль должна делиться на k.
 * <p>
 * Формат ввода
 * В единственной строке входных данных через пробел записаны три числа: n, k, d — изначальная прибыль,
 * количество учредителей компании и количество дней, которое вы собираетесь следить за прибылью
 * (1 ≤ n, k ≤ 109, 1 ≤ d ≤ 105)
 * НЕ гарантируется, что n делится на k.
 * <p>
 * Формат вывода
 * Выведите одно целое число x — прибыль компании через d дней. Первые цифры числа x должны совпадать с числом n.
 * Все префиксы числа x, которые длиннее числа n на 1, 2, … , d цифр, должны делиться на k.
 * Если возможных ответов несколько, выведите любой из них. Если ответа не существует, выведите − 1.
 */
public class Startup {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson1\\inputE_7.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int d = scanner.nextInt();

            int res = -1;
            boolean isExist = false;
            for (int j = 0; j <= 9; j++) {
                res = (n * 10) + j;
                if (res % k == 0) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                // Добавляем нужное количество нулей
                String result = String.valueOf(res);
                char[] l = new char[d - 1];
                Arrays.fill(l, '0');
                result += String.valueOf(l);
                System.out.println(result);
            } else {
                System.out.println("-1");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
