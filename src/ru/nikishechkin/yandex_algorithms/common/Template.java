package ru.nikishechkin.yandex_algorithms.common;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class Template {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("resources\\lesson3\\input*.txt")) {
            Scanner scanner = new Scanner(reader);
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int d = scanner.nextInt();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

