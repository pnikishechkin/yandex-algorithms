package ru.nikishechkin.yandex_algorithms.lesson_2.b_fish_seller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * B. Продавец рыбы
 *
 * Вася решил заняться торговлей рыбой.
 * С помощью методов машинного обучения он предсказал цены на рыбу на N дней вперёд.
 * Он решил, что в один день он купит рыбу, а в один из следующих дней — продаст
 * (то есть совершит или ровно одну покупку и продажу или вообще не совершит покупок и продаж, если это не
 * принесёт ему прибыли). К сожалению, рыба — товар скоропортящийся и разница между номером дня продажи и номером
 * дня покупки не должна превышать K.
 *
 * Определите, какую максимальную прибыль получит Вася.
 *
 * Формат ввода
 * В первой строке входных данных задаются числа N и K (1 ≤ N ≤ 10000, 1 ≤ K ≤ 100).
 *
 * Во второй строке задаются цены на рыбу в каждый из N дней. Цена — целое число, которое может находится
 * в пределах от 1 до 10^9.
 *
 * Формат вывода
 * Выведите одно число — максимальную прибыль, которую получит Вася.
 */
public class FishSeller {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson2\\inputB.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] price = new int[n];

            for (int i = 0; i < n; i++) {
                price[i] = scanner.nextInt();
            }

            int max = 0;
            int delta = 0;

            for (int i = 0; i < n - 1; i++) {
                for (int j = 1; j <= k; j++) {
                    if ((i + j) >= n) {
                        break;
                    }
                    delta = price[i + j] - price[i];
                    if (delta > max) {
                        max = delta;
                    }
                }
            }

            if (max < 0) {
                max = 0;
            }

            System.out.println(max);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
