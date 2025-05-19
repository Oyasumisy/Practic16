package utils;

import java.io.*;
import java.util.Scanner;

public class ConfigUtils {
    public static int boardSize = 3;
    public static String playerX = "Player X";
    public static String playerO = "Player O";

    public static void configure(Scanner scanner) {
        System.out.print("Введіть ім'я гравця X: ");
        playerX = scanner.nextLine();
        System.out.print("Введіть ім'я гравця O: ");
        playerO = scanner.nextLine();
        System.out.print("Введіть розмір поля (3-9): ");
        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            scanner.nextLine();
            if (size >= 3 && size <= 9) boardSize = size;
        }
        System.out.println("Налаштування оновлено.");
    }

    public static void save() throws IOException {
        try (PrintWriter pw = new PrintWriter("config.txt")) {
            pw.println(boardSize);
            pw.println(playerX);
            pw.println(playerO);
        }
    }

    public static void load() throws IOException {
        File file = new File("config.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                boardSize = Integer.parseInt(br.readLine());
                playerX = br.readLine();
                playerO = br.readLine();
            }
        }
    }
}