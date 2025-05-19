import java.io.IOException;
import java.util.Scanner;
import utils.ConfigUtils;
import utils.StatsUtils;
import utils.BoardUtils;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ConfigUtils.load();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Грати");
            System.out.println("2. Налаштування");
            System.out.println("3. Переглянути статистику");
            System.out.println("4. Очистити статистику");
            System.out.println("5. Вийти");
            System.out.print("Виберіть опцію: ");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Невірний ввід.");
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> BoardUtils.playGame(scanner);
                case 2 -> ConfigUtils.configure(scanner);
                case 3 -> StatsUtils.showStats();
                case 4 -> StatsUtils.clearStats();
                case 5 -> {
                    ConfigUtils.save();
                    System.out.println("Збережено. Вихід.");
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }
}