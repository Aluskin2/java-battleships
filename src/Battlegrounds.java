import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Battlegrounds 
{
    public static final String[][] playerBattleGround = new String[Const.BATTLEGROUND_SIZE][Const.BATTLEGROUND_SIZE];
    public static final String[][] playerShotsGround = new String[Const.BATTLEGROUND_SIZE][Const.BATTLEGROUND_SIZE];
    public static final String[][] computerBattleGround = new String[Const.BATTLEGROUND_SIZE][Const.BATTLEGROUND_SIZE];
    private static final Random random = new Random();

    public static void createBattlegrounds()
    {
        createBattleground(playerBattleGround);
        createBattleground(playerShotsGround);
        createBattleground(computerBattleGround);
    }

    public static void printBattlegrounds()
    {
        Common.addEmptyLine();

        System.out.print("POLE GRACZA");
        printBattleground(playerBattleGround);

        Common.addEmptyLine();
        System.out.print("STRZAŁY GRACZA / POLE KOMPUTERA");
        printBattleground(playerShotsGround);

        Common.addEmptyLine();
        System.out.printf("Twoje statki: %s | Statki komputera: %s", BattleShips.playerShips, BattleShips.computerShips);
    }

    private static void createBattleground(String[][] battleground)
    {
        for (String[] strings : battleground) {
            Arrays.fill(strings, Const.EMPTY_FIELD_SYMBOL);
        }
    }

    private static void printBattleground(String[][] battleground)
    {
        Common.addEmptyLine();
        System.out.print("  ");

        for (int i = 1; i <= Const.BATTLEGROUND_SIZE; i++) {
            System.out.print(i);
        }

        Common.addEmptyLine();

        for (int x = 0; x < battleground.length; x++) {
            System.out.print((x + 1) + "|");

            for (int y = 0; y < battleground[x].length; y++) {
                System.out.print(battleground[x][y]);
            }

            System.out.println("|" + (x + 1));
        }

        System.out.print("  ");

        for (int i = 1; i <= Const.BATTLEGROUND_SIZE; i++) {
            System.out.print(i);
        }

        Common.addEmptyLine();
    }

    public static void deployPlayerBattleShips()
    {
        int y;
        int x;

        System.out.printf("Graczu, rozmieść swoje statki! (ilość: %d)", BattleShips.playerShips);
        Common.addEmptyLine();
        System.out.printf("Wymiary pola bitwy: %s/%s", Const.BATTLEGROUND_SIZE, Const.BATTLEGROUND_SIZE);
        Common.addEmptyLine();

        for (int i = 1; i <= BattleShips.playerShips;) {
            System.out.printf("Rozmieść %d statek", i);
            Common.addEmptyLine();

            x = Validator.getValidCoordinate("X");
            y = Validator.getValidCoordinate("Y");

            if (Objects.equals(Battlegrounds.playerBattleGround[x - 1][y - 1], Const.SHIP_SYMBOL)) {
                System.out.print("Nie możesz umieścić dwóch statków w tym samym miejscu!");
            }

            Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.SHIP_SYMBOL;
            i++;
        }
    }

    public static void deployComputerBattleShips()
    {
        Common.addEmptyLine();
        System.out.println("Rozmieszczam sześć statków komputera na planszy...");

        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;
            int y = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;

            if (Objects.equals(Battlegrounds.computerBattleGround[x-1][y-1], Const.EMPTY_FIELD_SYMBOL)) {
                Battlegrounds.computerBattleGround[x-1][y-1] = Const.SHIP_SYMBOL;
                System.out.println(i + ". statek rozmieszczony");
                i++;
            }
        }
    }
}
