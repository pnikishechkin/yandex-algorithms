package ru.nikishechkin.yandex_algorithms.lesson_4.a_search_in_mas;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * A. Быстрый поиск в массиве
 * <p>
 * Дан массив из N целых чисел. Все числа от −109 до 109.
 * Нужно уметь отвечать на запросы вида “Cколько чисел имеют значения отL доR?”.
 * <p>
 * Формат ввода
 * Число N (1≤N≤10^5). Далее N целых чисел.
 * Затем число запросов K (1 ≤ K ≤ 10^5).
 * Далее K пар чисел L,R (−10^9 ≤ L ≤ R ≤ 10^9) — собственно запросы.
 * <p>
 * Формат вывода
 * Выведите K чисел — ответы на запросы.
 */
public class SearchInMas_v1 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson4\\inputA_2.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            scanner.nextLine();
            String[] str = scanner.nextLine().split(" ");

            int[] in1 = new int[str.length];
            Integer[] in2 = new Integer[str.length];
            //LinkedList<Integer> input = new LinkedList<>();
            //TreeMap<Integer, Integer> inp = new TreeMap<>();
            int val = 0;

            for (int i = 0; i < str.length; i++) {
                val = Integer.parseInt(str[i]);
                //input.add(val);
                in1[i] = val;
                in2[i] = val;
            }

            Arrays.sort(in1);

            Arrays.sort(in2, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2.compareTo(o1);
                }
            });

            //Arrays.sort(in, Collections.reverseOrder());

//            System.out.println(n);
            //System.out.println(Arrays.toString(in1));
            //System.out.println(Arrays.toString(in2));

            int k = scanner.nextInt();
            int l = 0;
            int r = 0;
            int i1, i2, res;

            for (int i = 0; i < k; i++) {
                l = scanner.nextInt();
                r = scanner.nextInt();
                i1 = getFirstIndex(l, in1);
                i2 = n - getLastIndex(r, in2) - 1;
                res = i2 - i1 + 1;
                if (res < 0) res = 0;

                //System.out.print(i1 + " " + i2 + " | " + res + " ");
                //System.out.println();
                System.out.print(res + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int getFirstIndex(int l, int[] mas) {
        int mid = mas.length / 2;
        int start = 0;
        int last = mas.length - 1;

        while (mas[mid] != l && start <= last) {
            if (l > mas[mid]) {
                start = mid + 1;
            } else {
                last = mid - 1;
            }
            mid = (start + last) / 2;
        }

        if (start > last) {
            // Элемент не найден, возвращаем следующий по счету элемент
            return mid + 1;
        } else {
            // Элемент найден
            return mid;
        }
    }

    public static int getLastIndex(int l, Integer[] mas) {
        int mid = mas.length / 2;
        int start = 0;
        int last = mas.length - 1;
        boolean find = false;

        while (mas[mid] != l && start <= last) {
            if (l < mas[mid]) {
                start = mid + 1;
            } else {
                last = mid - 1;
            }
            mid = (start + last) / 2;
        }

        if (start > last) {
            // Элемент не найден, возвращаем следующий по счету элемент
            return mid + 1;
        } else {
            // Элемент найден
            return mid;
        }
    }
}
