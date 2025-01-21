package ru.nikishechkin.yandex_algorithms.lesson_3.f_replacing_words;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
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
 * Количество слов в тексте не превосходит 10^5, а суммарное количество букв в них — 10^6.
 * <p>
 * Формат вывода
 * Выведите текст, в котором осуществлены замены.
 */
public class ReplacingWords_v4 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputF_3.txt")) {
            Scanner scanner = new Scanner(reader);
            String[] dict = scanner.nextLine().split(" ");

            Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() != o2.length())
                        return o1.length() - o2.length();
                    else
                        return o1.compareTo(o2);
                }
            };

            TreeSet<String> set = new TreeSet<>(comparator);

            for (int i = 0; i < dict.length; i++) {
                set.add(dict[i]);
            }

            // System.out.println(set.toString());

            FileWriter fw = new FileWriter("resources\\lesson3\\output.txt");

            HashMap<String, String> dictionary = new HashMap<>();

            String[] text = scanner.nextLine().split(" ");
            String res;
            for (int i = 0; i < text.length; i++) {

                if (dictionary.containsKey(text[i])){
                    fw.write(dictionary.get(text[i])+ " ");
                    continue;
                }

                res = text[i];
                for (String d : set) {
                    if (res.startsWith(d)) {
                        res = d;
                        break;
                    }
                }
                dictionary.put(text[i], res);
                fw.write(res + " ");
            }

            fw.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
