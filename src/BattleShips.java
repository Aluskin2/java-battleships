import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class BattleShips
{
    public static final String shipSymbol = "S";
    public static final String shotSymbol = "X";
    public static final String emptyFieldSymbol = "O";

    public static int battleGroundColumns = 6;
    public static int battleGroundRows = 6;

    public static int playerShips = 6;
    public static int computerShips = 6;

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
        createBattleground(playerBattleGround);
        createBattleground(playerShotsGround);
        createBattleground(computerBattleGround);
    }

    private static void createBattleground(String[][] battleground)
    {
        for (String[] strings : battleground) {
            Arrays.fill(strings, emptyFieldSymbol);
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

        System.out.println("Graczu, rozmieść swoje statki! (ilość: 6)");
        System.out.printf("Wymiary pola bitwy: %s/%s", battleGroundColumns, battleGroundRows);
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
                    case emptyFieldSymbol -> {
                        playerBattleGround[x - 1][y - 1] = shipSymbol;
                        i++;
                    }
                    case shipSymbol -> System.out.println("Nie możesz umieścić dwóch statków w tym samym miejscu!");
                }
            } else {
                System.out.println("Nie możesz umieścić statku poza wymiarami pola bitwy!");
            }
        }

        System.out.println();
        System.out.print("POLE GRACZA");
        printBattleground(playerBattleGround);

        System.out.println();
        System.out.print("STRZAŁY GRACZA / POLE KOMPUTERA");
        printBattleground(playerShotsGround);
    }

    public static void deployComputerBattleShips()
    {
        Random random = new Random();

        System.out.println();
        System.out.println("Rozmieszczam sześć statków komputera na planszy...");

        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = random.nextInt(battleGroundColumns - 1) + 1;
            int y = random.nextInt(battleGroundRows - 1) + 1;

            if (Objects.equals(computerBattleGround[x-1][y-1], emptyFieldSymbol)) {
                computerBattleGround[x-1][y-1] = shipSymbol;
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

        System.out.println();
        System.out.println("Twoja kolej na atak! podaj koordynaty!");

        do {
            System.out.print("Podaj wartość osi X do ataku: ");
            x = input.nextInt();
            System.out.print("Podaj wartość osi Y do ataku: ");
            y = input.nextInt();

            if (
                !(
                  x > 0
                  && y > 0
                  && x <= battleGroundRows
                  && y <= battleGroundColumns
                )
            ) {
                System.out.println("Nie możesz wykonywać ataku na koordynaty poza wymiarami pola bitwy!");
                continue;
            }

            if (Objects.equals(playerShotsGround[x - 1][y - 1], shotSymbol)) {
                System.out.println("Już wykonywałeś atak na te koordynaty, spróbuj ponownie!");
                continue;
            }

            switch (computerBattleGround[x - 1][y - 1]) {
                case shipSymbol -> {
                    System.out.print("Brawo, zatopiłeś statek komputera!");

                    computerBattleGround[x - 1][y - 1] = emptyFieldSymbol;
                    playerShotsGround[x - 1][y - 1] = shotSymbol;
                    --computerShips;
                    retryAttack = false;
                }
                case emptyFieldSymbol -> {
                    System.out.print("Niestety, spudłowałeś");

                    playerShotsGround[x - 1][y - 1] = shotSymbol;
                    retryAttack = false;
                }
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
                case shipSymbol -> {
                    System.out.print("Ups, komputer zatopił Twój statek!");

                    playerBattleGround[x - 1][y - 1] = shotSymbol;
                    --playerShips;
                    retryAttack = false;
                }
                case emptyFieldSymbol -> {
                    System.out.print("komputer spudłował!");

                    playerBattleGround[x - 1][y - 1] = shotSymbol;
                    retryAttack = false;
                }
                case shotSymbol -> { // jeśli komputer zaatakował to samo miejsce, powtarzamy atak
                    System.out.print("komputer zaatkował to samo miejsce co wcześniej!, jeszcze raz...");
                }
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

        System.out.println();
        System.out.print("STRZAŁY GRACZA / POLE KOMPUTERA");
        printBattleground(playerShotsGround);

        System.out.println();
        System.out.printf("Twoje statki: %s | Statki komputera: %s", playerShips, computerShips);
    }

    public static void endGame()
    {
        if (BattleShips.computerShips == 0) {
            System.out.println();
            System.out.println("Gratulacje, wygrałeś!");
        }

        if (BattleShips.playerShips == 0) {
            System.out.println();
            System.out.println("Przykro mi, przegrałeś. Spróbuj ponownie!");
        }
    }
}
