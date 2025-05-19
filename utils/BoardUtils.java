package utils;

import java.io.IOException;
import java.util.Scanner;

public class BoardUtils {
    private static char[][] board;

    public static void playGame(Scanner scanner) throws IOException {
        board = initBoard();
        char currentPlayer = 'X';

        while (true) {
            printBoard();
            int[] move = getMove(scanner);
            if (move[0] == -1) return;
            board[move[0]][move[1]] = currentPlayer;
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Гравець " + currentPlayer + " переміг!");
                StatsUtils.saveStat(currentPlayer, ConfigUtils.boardSize, ConfigUtils.playerX, ConfigUtils.playerO);
                break;
            }
            if (isDraw()) {
                printBoard();
                System.out.println("Нічия!");
                StatsUtils.saveStat('-', ConfigUtils.boardSize, ConfigUtils.playerX, ConfigUtils.playerO);
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static char[][] initBoard() {
        int size = ConfigUtils.boardSize;
        char[][] b = new char[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                b[i][j] = '-';
        return b;
    }

    private static void printBoard() {
        for (char[] row : board) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
    }

    private static int[] getMove(Scanner scanner) {
        int size = ConfigUtils.boardSize;
        while (true) {
            System.out.print("Введіть рядок і стовпець (1-" + size + ", або 0 0 для виходу): ");
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Невірний ввід.");
                continue;
            }
            int r = scanner.nextInt();
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Невірний ввід.");
                continue;
            }
            int c = scanner.nextInt();
            scanner.nextLine();
            if (r == 0 && c == 0) return new int[]{-1, -1};
            r--; c--;
            if (r >= 0 && r < size && c >= 0 && c < size && board[r][c] == '-') return new int[]{r, c};
            System.out.println("Невірний хід.");
        }
    }

    private static boolean checkWin(char p) {
        int size = ConfigUtils.boardSize;
        for (int i = 0; i < size; i++) {
            boolean row = true, col = true;
            for (int j = 0; j < size; j++) {
                row &= board[i][j] == p;
                col &= board[j][i] == p;
            }
            if (row || col) return true;
        }
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < size; i++) {
            diag1 &= board[i][i] == p;
            diag2 &= board[i][size - i - 1] == p;
        }
        return diag1 || diag2;
    }

    private static boolean isDraw() {
        for (char[] row : board)
            for (char c : row)
                if (c == '-') return false;
        return true;
    }
}