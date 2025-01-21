package ru.nikishechkin.yandex_algorithms.lesson_1.b_football_commentator;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * B. Футбольный комментатор
 *
 * Раунд плей-офф между двумя командами состоит из двух матчей. Каждая команда проводит по одному матчу «дома»
 * и «в гостях». Выигрывает команда, забившая большее число мячей. Если же число забитых мячей совпадает,
 * выигрывает команда, забившая больше мячей «в гостях». Если и это число мячей совпадает, матч переходит
 * в дополнительный тайм или серию пенальти.
 *
 * Вам дан счёт первого матча, а также счёт текущей игры (которая ещё не завершилась). Помогите комментатору
 * сообщить, сколько голов необходимо забить первой команде, чтобы победить, не переводя игру в дополнительное время.
 *
 * Формат ввода
 * В первой строке записан счёт первого мачта в формате G1:G2, где G1 — число мячей, забитых первой командой,
 * а G2 — число мячей, забитых второй командой.
 * Во второй строке записан счёт второго (текущего) матча в аналогичном формате.
 * Все числа в записи счёта не превышают 5.
 * В третьей строке записано число 1, если первую игру первая команда провела «дома», или 2, если «в гостях».
 *
 * Формат вывода
 * Выведите единственное целое число "— необходимое количество мячей.
 */
public class FootballCommentator {
    public static void main(String[] args) {

        try (FileReader reader = new FileReader("resources\\lesson1\\inputB.txt")) {
            Scanner scanner = new Scanner(reader);
            int res = 0;

            String str = scanner.next();

            char[] ch = str.toCharArray();
            int m1_g1 = Character.getNumericValue(ch[0]);
            int m1_g2 = Character.getNumericValue(ch[2]);
            int m1_dif = m1_g2 - m1_g1;

            str = scanner.next();
            ch = str.toCharArray();
            int m2_g1 = Character.getNumericValue(ch[0]);
            int m2_g2 = Character.getNumericValue(ch[2]);
            int m2_dif = m2_g2 - m2_g1;

            int home = scanner.nextInt();

            if ((m1_dif + m2_dif) < 0) { // Если первая команда побеждает по сумме двух встреч
                res = 0;
            } else {
                // Определяем, сколько необходимо забить в текущем матче, чтобы по сумме встреч счет был ничейным
                res = m1_dif + m2_dif;
                m2_g1 += res;

                // Если первую игру команда провела дома (т.е. текущий матч в гостях)
                if (home == 1 && m2_g1 <= m1_g2) {
                    res += 1;
                }
                // Если первую игру команда провела в гостях (т.е. текущий матч дома)
                if (home == 2 && m1_g1 <= m2_g2) {
                    res += 1;
                }
            }
            System.out.println(res);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
