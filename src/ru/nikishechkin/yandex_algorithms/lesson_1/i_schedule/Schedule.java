package ru.nikishechkin.yandex_algorithms.lesson_1.i_schedule;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * I. Расписание
 *
 * Как же Илье надоело учиться! Сначала школа, потом университет...
 * Вот, наконец, наступил тот долгожданный день, когда Илье утром не надо ехать на учебу.
 * Но, к несчастью для Ильи, оказалось, что после окончания университета начинается самое трудное —
 * надо устраиваться на работу.
 *
 * Во всемирно известной фирме «Goondex», в которую устроился Илья, принято очень много работать, в частности,
 * для сотрудников установлена шестидневная рабочая неделя. Но, в качестве бонуса, «Goondex» каждый год предлагает
 * своим сотрудникам выбрать любой день недели в качестве выходного.
 * В свою очередь, оставшиеся шесть дней недели будут рабочими.
 *
 * Илья сообразил, что с учётом государственных праздников (которые всегда являются выходными) с помощью
 * правильного выбора выходного дня недели можно варьировать количество рабочих дней в году.
 * Теперь он хочет знать, какой день недели ему следует выбрать в качестве выходного, чтобы отдыхать как можно
 * больше дней в году, или, наоборот, демонстрировать чудеса трудолюбия, работая по максимуму.
 *
 * Формат ввода
 * В первой строке входных данных находится одно целое число N (0 ≤ N ≤ 366) — количество государственных праздников.
 * Во второй строке содержится одно целое число year (1800 ≤ year ≤ 2100) — год, в который необходимо помочь Илье.
 * В каждой из последующих N строк расположено по паре чисел day month (day — целое число, month — слово, между day
 * и month ровно один пробел), обозначающих, что день day месяца month является государственным праздником.
 * В последней строке расположено слово day_of_week — день недели первого января в год year.
 * Гарантируется, что все даты указаны корректно (в том числе указанный день недели первого января действительно
 * является днём недели первого января соответствующего года year) и все дни государственных праздников различны.
 *
 * Формат вывода
 * Выведите через пробел два дня недели — лучший и худший варианты дней недели для выходного (то есть дни недели,
 * для которых достигается соответственно максимальное и минимальное количество выходных дней в году).
 * Если возможных вариантов ответа несколько, выведите любой из них.
 */
public class Schedule {

    public static void main(String[] args) {

        try (FileReader reader = new FileReader("resources\\lesson1\\inputI.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int year = scanner.nextInt();

            Map<Integer, List<Integer>> holidays = new HashMap<>();
            int day = 0, month = 0;
            String monthName;

            // Чтение праздничных дней
            for (int i = 0; i < n; i++) {
                day = scanner.nextInt();
                monthName = scanner.next();

                month = getMonthNum(monthName);

                if (holidays.containsKey(month)) {
                    holidays.get(month).add(day);
                } else {
                    holidays.put(month, new ArrayList<>());
                    holidays.get(month).add(day);
                }
            }

            //System.out.println(holidays);

            int[] countDays = new int[7];
            int[] countHolidays = new int[7];
            String firstDay = scanner.next();

            int curMonth = 1, dayInMonth = 0;
            int weekday = getWeekDayNum(firstDay); // 0 - Понедельник, 1 - Вторник, и т.д.

            // Определение количества дней в году (и в феврале), в зависимости от високосности
            int countDaysInYear = 365;
            int countDaysInFeb = 28;
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                // Високосный
                countDaysInYear = 366;
                countDaysInFeb = 29;
            }

            // Подсчет количества каждого дня недели в году
            for (int i = 0; i < countDaysInYear; i++) {

                countDays[weekday]++;

                dayInMonth++;

                // Обновляем количество праздничных дней недели
                if (holidays.containsKey(curMonth) && holidays.get(curMonth).contains(dayInMonth)) {
                    countHolidays[weekday]++;
                }

                if (weekday == 6) {
                    weekday = 0;
                } else {
                    weekday++;
                }

                // Обнуление дня месяца
                if (curMonth == 1 || curMonth == 3 || curMonth == 5 || curMonth == 7 || curMonth == 8 ||
                        curMonth == 10 || curMonth == 12) {
                    if (dayInMonth == 31) {
                        dayInMonth = 0;
                        curMonth++;
                    }
                } else if (curMonth == 2) {
                    if (dayInMonth == countDaysInFeb) {
                        dayInMonth = 0;
                        curMonth++;
                    }
                } else {
                    if (dayInMonth == 30) {
                        dayInMonth = 0;
                        curMonth++;
                    }
                }
            }

            //System.out.println(Arrays.toString(countDays));
            //System.out.println(Arrays.toString(countHolidays));

            int[] res = new int[7];
            int t = 0;

            // Подсчет количества выходных, в случае выбора каждого дня. Выбор наилучшего и наихудшего вариантов
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (i != j) {
                        t += countHolidays[j];
                    }
                }
                res[i] = countDays[i] + t;
                t = 0;
            }

            int indexMin = 0, indexMax = 0;
            for (int i = 0; i < 7; i++) {
                if (res[indexMin] < res[i]) {
                    indexMin = i;
                }
                if (res[indexMax] > res[i]) {
                    indexMax = i;
                }
            }

            //System.out.println(Arrays.toString(res));
            System.out.println(getWeekDayName(indexMin));
            System.out.println(getWeekDayName(indexMax));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Получить номер месяца по его английскому названию. Нумерация - с 1
      * @param monthName
     * @return
     */
    private static int getMonthNum(String monthName) {
        int monthNum = 0;
        switch (monthName) {
            case "January":
                monthNum = 1;
                break;
            case "February":
                monthNum = 2;
                break;
            case "March":
                monthNum = 3;
                break;
            case "April":
                monthNum = 4;
                break;
            case "May":
                monthNum = 5;
                break;
            case "June":
                monthNum = 6;
                break;
            case "July":
                monthNum = 7;
                break;
            case "August":
                monthNum = 8;
                break;
            case "September":
                monthNum = 9;
                break;
            case "October":
                monthNum = 10;
                break;
            case "November":
                monthNum = 11;
                break;
            case "December":
                monthNum = 12;
                break;
        }
        return monthNum;
    }

    /**
     * Получить номер дня недели по его английскому названию. Нумерация - с 0
     * @param weekDayName
     * @return
     */
    private static int getWeekDayNum(String weekDayName) {
        int weekDayNum = 0;
        switch (weekDayName) {
            case "Monday":
                weekDayNum = 0;
                break;
            case "Tuesday":
                weekDayNum = 1;
                break;
            case "Wednesday":
                weekDayNum = 2;
                break;
            case "Thursday":
                weekDayNum = 3;
                break;
            case "Friday":
                weekDayNum = 4;
                break;
            case "Saturday":
                weekDayNum = 5;
                break;
            case "Sunday":
                weekDayNum = 6;
                break;
            default:
                weekDayNum = -1;
                break;
        }
        return weekDayNum;
    }

    private static String getWeekDayName(int weekDayNum) {
        String weekDayName;
        switch (weekDayNum) {
            case 0:
                weekDayName = "Monday";
                break;
            case 1:
                weekDayName = "Tuesday";
                break;
            case 2:
                weekDayName = "Wednesday";
                break;
            case 3:
                weekDayName = "Thursday";
                break;
            case 4:
                weekDayName = "Friday";
                break;
            case 5:
                weekDayName = "Saturday";
                break;
            case 6:
                weekDayName = "Sunday";
                break;
            default:
                weekDayName = "Undefined";
                break;
        }
        return weekDayName;
    }
};

