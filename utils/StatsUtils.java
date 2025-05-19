package utils;

import java.io.*;
import java.time.LocalDateTime;

public class StatsUtils {
    public static void saveStat(char winner, int boardSize, String playerX, String playerO) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("game_history.txt", true))) {
            pw.println(LocalDateTime.now() + " | " + playerX + " (X) vs " + playerO + " (O) | Розмір поля: " + boardSize + "x" + boardSize + " | Переможець: " +
                    (winner == 'X' ? playerX : winner == 'O' ? playerO : "Нічия"));
        }
    }

    public static void showStats() throws IOException {
        File file = new File("game_history.txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) System.out.println(line);
            }
        } else {
            System.out.println("Статистика відсутня.");
        }
    }

    public static void clearStats() throws IOException {
        File file = new File("game_history.txt");
        if (file.exists()) {
            try (PrintWriter pw = new PrintWriter(file)) {
                pw.print("");
            }
            System.out.println("Статистику очищено.");
        } else {
            System.out.println("Файл статистики не знайдено.");
        }
    }
}
