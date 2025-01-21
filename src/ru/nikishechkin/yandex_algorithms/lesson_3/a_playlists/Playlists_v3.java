package ru.nikishechkin.yandex_algorithms.lesson_3.a_playlists;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * A. Плейлисты
 *
 * Костя успешно прошел собеседование и попал на стажировку в отдел разработки сервиса «Музыка».
 * Конкретно ему поручили такое задание — научиться подбирать плейлист для группы друзей, родственников или коллег.
 * При этом нужно подобрать такой плейлист, в который входят исключительно нравящиеся всем членам группы песни.
 * Костя очень хотел выполнить это задание быстро и качественно, но у него не получается. Помогите ему написать
 * программу, которая составляет плейлист для группы людей.
 * <p>
 * Формат ввода
 * В первой строке расположено одно натуральное число n (1 ≤ n ≤ 2 ⋅ 10^5), где n – количество человек в группе.
 * В следующих 2⋅n строках идет описание любимых плейлистов членов группы. По 2 строки на каждого участника.
 * В первой из этих 2-х строк расположено число k_i — количество любимых треков i-го члена группы.
 * В следующей строке расположено k_i строк через пробел — названия любимых треков i-го участника группы.
 * Каждый трек в плейлисте задан в виде строки, все строки уникальны, сумма длин строк не превосходит 2⋅10^6.
 * Строки содержат большие и маленькие латинские буквы и цифры.
 * <p>
 * Формат вывода
 * Выведите количество, а затем сам список песен через пробел — список треков, которые нравятся каждому участнику
 * группы. Ответ необходимо отсортировать в лексикографическом порядке!
 */
public class Playlists_v3 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputA_21.txt")) {

//            long a = System.currentTimeMillis();

            Scanner scanner = new Scanner(reader);
            int n = Integer.parseInt(scanner.nextLine());

            TreeSet<String> res = new TreeSet<>();
            TreeSet<String> temp = new TreeSet<>();

            for (int i = 0; i < n; i++) {

                String k = scanner.nextLine();
                String values = scanner.nextLine();

                if (i == 0) {
                    res.addAll(Arrays.asList(values.split(" ")));
                } else {
                    temp.clear();
                    temp.addAll(Arrays.asList(values.split(" ")));
                    res.retainAll(temp);
                }
            }

            FileWriter fw = new FileWriter("resources\\lesson3\\output.txt");
            Integer count = res.size();
            fw.write(count.toString() + "\n");
            //System.out.println(res.size());
            for (String s : res) {
                fw.write(s + " ");
                // System.out.print(s + " ");
            }
            fw.close();

//            long b = System.currentTimeMillis();
//            System.out.println();
//            System.out.println(b - a);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
