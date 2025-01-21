package ru.nikishechkin.yandex_algorithms.lesson_3.f_replacing_words;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * F. Замена слов
 * <p>
 * С целью экономии чернил в картридже принтера было принято решение укоротить некоторые слова в тексте.
 * Для этого был составлен словарь слов, до которых можно сокращать более длинные слова. Слово из текста можно
 * сократить, если в словаре найдется слово, являющееся началом слова из текста. Например, если в списке есть слово
 * "лом", то слова из текста "ломбард", "ломоносов" и другие слова, начинающиеся на "лом", можно сократить до "лом".
 * <p>
 * Если слово из текста можно сократить до нескольких слов из словаря, то следует сокращать его до самого
 * короткого слова.
 * <p>
 * Формат ввода
 * В первой строке через пробел вводятся слова из словаря, слова состоят из маленьких латинских букв. Гарантируется,
 * что словарь не пуст и количество слов в словаре не превышет 1000, а длина слов — 100 символов.
 * <p>
 * Во второй строке через пробел вводятся слова текста (они также состоят только из маленьких латинских букв).
 * Количество слов в тексте не превосходит 105, а суммарное количество букв в них — 106.
 * <p>
 * Формат вывода
 * Выведите текст, в котором осуществлены замены.
 */
public class ReplacingWords_v2 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputF_3.txt")) {
            Scanner scanner = new Scanner(reader);
            String[] dict = scanner.nextLine().split(" ");
/*
            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            };
*/
            TreeSet<String> set = new TreeSet<>();

            for (int i = 0; i < dict.length; i++) {
                set.add(dict[i]);
            }

            HashMap<String, String> dictionary = new HashMap<>();

            String[] text = scanner.nextLine().split(" ");
            String res;
            for (int i = 0; i < text.length; i++) {

                if (dictionary.containsKey(text[i])){
                    System.out.print(dictionary.get(text[i])+ " ");
                    continue;
                }

                res = text[i];
                for (String d : set) {
                    if (res.startsWith(d)) {
                        res = d;
                    }
                    if (res.length() == 1) break;
                }
                dictionary.put(text[i], res);
                System.out.print(res + " ");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
