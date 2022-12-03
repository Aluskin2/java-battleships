import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class BattleShips
{
    public static int battleGroundColumns = 8;
    public static int battleGroundRows = 8;

    public static int playerShips;
    public static int computerShips;

    public static String[][] playerBattleGround = new String[battleGroundColumns][battleGroundRows];
    public static String[][] playerShotsGround = new String[battleGroundColumns][battleGroundRows];
    public static String[][] computerBattleGround = new String[battleGroundColumns][battleGroundRows];

    public static void main(String[] args)
    {
        createBattlegrounds();

        deployPlayerBattleShips();
        deployComputerBattleShips();

        do {
            battle();
        } while (BattleShips.playerShips != 0 && BattleShips.computerShips != 0);

        endGame();
    }

    public static void createBattlegrounds()
    {
        System.out.print("POLE GRACZA");
        System.out.print("  ");

        createBattleground(playerBattleGround);
        printBattleground(playerBattleGround);
        System.out.println();

        System.out.print("STRZAŁY GRACZA / POLE KOMPUTERA");
        System.out.print("  ");

        createBattleground(playerShotsGround);
        printBattleground(playerShotsGround);
        System.out.println();

        createBattleground(computerBattleGround);
    }

    private static void createBattleground(String[][] battleground)
    {
        for (String[] strings : battleground) {
            Arrays.fill(strings, "O");
        }
    }

    public static void printBattleground(String[][] battleground)
    {
        System.out.println();
        System.out.print("  ");

        for (int i = 1; i <= battleGroundColumns; i++) {
            System.out.print(i);
        }

        System.out.println();

        for (int x = 0; x < battleground.length; x++) {
            System.out.print((x + 1) + "|");

            for (int y = 0; y < battleground[x].length; y++) {
                System.out.print(battleground[x][y]);
            }

            System.out.println("|" + (x + 1));
        }

        System.out.print("  ");

        for (int i = 1; i <= battleGroundColumns; i++) {
            System.out.print(i);
        }

        System.out.println();
    }

    public static void deployPlayerBattleShips()
    {
        Scanner input = new Scanner(System.in);
        BattleShips.playerShips = 6;

        System.out.print("Graczu, rozmieść swoje statki! (ilość: 6)");
        System.out.println();

        for (int i = 1; i <= BattleShips.playerShips;) {
            System.out.print("Podaj numer wiersza dla swojego " + i + " statku: ");
            int x = input.nextInt();
            System.out.print("Podaj numer kolumny dla swojego " + i + " statku: ");
            int y = input.nextInt();

            if (
              (x >= 1 && x <= battleGroundRows)
              && (y >= 1 && y <= battleGroundColumns)
            ) {
                switch (playerBattleGround[x - 1][y - 1]) {
                    case "O" -> {
                        playerBattleGround[x - 1][y - 1] = "S";
                        i++;
                    }
                    case "S" -> System.out.println("Nie możesz umieścić dwóch statków w tym samym miejscu!");
                }
            } else {
                System.out.println("Nie możesz umieścić statku poza wymiarami pola bitwy!");
            }
        }

        printBattleground(playerBattleGround);
    }

    public static void deployComputerBattleShips()
    {
        Random random = new Random();
        BattleShips.computerShips = 6;

        System.out.println("Rozmieszczam sześć statków komputera na planszy...");
        System.out.println();

        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = random.nextInt(battleGroundColumns - 1) + 1;
            int y = random.nextInt(battleGroundRows - 1) + 1;

            if (Objects.equals(computerBattleGround[x-1][y-1], "O")) {
                computerBattleGround[x-1][y-1] = "S";
                System.out.println(i + ". statek rozmieszczony");
                i++;
            }
        }
    }

    private static void playerAttack()
    {
        int x;
        int y;
        boolean retryAttack = true;

        Scanner input = new Scanner(System.in);
        System.out.println("Twoja kolej na atak! podaj koordynaty!");

        do {
            System.out.print("Podaj wartość osi X do ataku: ");
            x = input.nextInt();
            System.out.print("Podaj wartość osi Y do ataku: ");
            y = input.nextInt();

            if (
                x > 0
                && y > 0
                && x <= battleGroundRows
                && y <= battleGroundColumns
            ) {
                switch (computerBattleGround[x - 1][y - 1]) {
                    case "S" -> {
                        System.out.print("Brawo, zatopiłeś statek komputera!");
                        computerBattleGround[x - 1][y - 1] = "O";
                        playerShotsGround[x - 1][y - 1] = "X";
                        --computerShips;
                        retryAttack = false;
                    }
                    case "O" -> {
                        System.out.print("Niestety, spudłowałeś");
                        playerShotsGround[x - 1][y - 1] = "X";
                        retryAttack = false;
                    }
                }
            } else {
                System.out.println("Nie możesz wykonywać ataku na koordynaty poza wymiarami pola bitwy!");
            }
        } while (retryAttack);
    }

    private static void computerAttack()
    {
        boolean retryAttack = true;

        Random random = new Random();

        System.out.println();
        System.out.println("Kolej na atak komputera...");

        do {
            int x = random.nextInt(battleGroundColumns - 1) + 1;
            int y = random.nextInt(battleGroundRows - 1) + 1;

            switch (computerBattleGround[x - 1][y - 1]) {
                case "S" -> {
                    System.out.print("Ups, komputer zatopił Twój statek!");
                    playerBattleGround[x - 1][y - 1] = "X";
                    --playerShips;
                    retryAttack = false;
                }
                case "O" -> {
                    System.out.print("komputer spudłował!");
                    playerBattleGround[x - 1][y - 1] = "X";
                    retryAttack = false;
                }
                case "X" -> // jeśli komputer zaatakował to samo miejsce, powtarzamy atak
                        System.out.print("komputer zaatkował to samo miejsce co wcześniej!, jeszcze raz...");
            }
        } while (retryAttack);
    }

    public static void battle()
    {
        playerAttack();
        computerAttack();

        System.out.println();
        System.out.print("POLE GRACZA");
        printBattleground(playerBattleGround);

        System.out.print("STRZAŁY GRACZA / POLE KOMPUTERA");
        printBattleground(playerShotsGround);

        System.out.println();
        System.out.printf("Twoje statki: %s | Statki komputera: %s", playerShips, computerShips);
    }

    public static void endGame()
    {
        if (BattleShips.computerShips == 0) {
            System.out.println("Gratulacje, wygrałeś!");
        }

        if (BattleShips.playerShips == 0) {
            System.out.println("Przykro mi, przegrałeś. Spróbuj ponownie!");
        }
    }
}
