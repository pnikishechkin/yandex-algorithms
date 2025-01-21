package ru.nikishechkin.yandex_algorithms.lesson_3.a_playlists;

import java.io.FileReader;
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
public class Playlists_v2 {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\inputA_16.txt")) {

//            long a = System.currentTimeMillis();

            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();

            HashSet<String> res = new HashSet<>();
            HashSet<String> temp = new HashSet<>();

            for (int i = 0; i < n; i++) {
                int k = scanner.nextInt();
                temp.clear();
                for (int j = 0; j < k; j++) {
                    String str = scanner.next();

                    if (i == 0) {
                        res.add(str);
                    } else if (res.contains(str)) {
                        temp.add(str);
                    }
                }
                if (i != 0) res.retainAll(temp);
                //System.out.println(res);
            }


//             ArrayList<String> list = new ArrayList<>(res);
//             Collections.sort(list);
            Object[] list = res.toArray();
            Arrays.sort(list);

            System.out.println(list.length);
            for (Object s : list) {
                System.out.print(s + " ");
            }

//            long b = System.currentTimeMillis();
//            System.out.println();
//            System.out.println(b - a);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
