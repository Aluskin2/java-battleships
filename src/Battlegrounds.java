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

    private static void createBattleground(String[][] battleground)
    {
        for (String[] strings : battleground) {
            Arrays.fill(strings, Const.EMPTY_FIELD_SYMBOL);
        }
    }

    public static void printBattlegrounds()
    {
        Common.addEmptyLine();
        System.out.print("PLAYER FIELD");
        printBattleground(playerBattleGround);

        Common.addEmptyLine();
        System.out.print("PLAYER SHOOTS / COMPUTER FIELD");
        printBattleground(playerShotsGround);

        Common.addEmptyLine();
        System.out.printf("Your ships: %s | Computer ships: %s", BattleShips.playerShips, BattleShips.computerShips);
    }

    private static void printBattleground(String[][] battleground)
    {
        Common.addEmptyLine();
        printBattlegroundHeaderOrFooter();

        Common.addEmptyLine();
        printBattlegroundBody(battleground);

        printBattlegroundHeaderOrFooter();
        Common.addEmptyLine();
    }

    private static void printBattlegroundHeaderOrFooter()
    {
        System.out.print("  ");

        for (int i = 1; i <= Const.BATTLEGROUND_SIZE; i++) {
            System.out.print(i);
        }
    }

    private static void printBattlegroundBody(String[][] battleground)
    {
        for (int x = 0; x < battleground.length; x++) {
            System.out.print((x + 1) + "|");

            for (int y = 0; y < battleground[x].length; y++) {
                System.out.print(battleground[x][y]);
            }

            System.out.println("|" + (x + 1));
        }
    }

    public static void deployBattleShips()
    {
        deployPlayerBattleShips();
        deployComputerBattleShips();
    }

    private static void deployPlayerBattleShips()
    {
        int y;
        int x;

        System.out.printf("Player, deploy your ships! (Count: %d)", BattleShips.playerShips);
        Common.addEmptyLine();
        System.out.printf("Battleground size: %s/%s", Const.BATTLEGROUND_SIZE, Const.BATTLEGROUND_SIZE);
        Common.addEmptyLine();

        for (int i = 1; i <= BattleShips.playerShips;) {
            System.out.printf("Deploy %d ship", i);
            Common.addEmptyLine();

            x = Validator.getValidCoordinate("X");
            y = Validator.getValidCoordinate("Y");

            if (Objects.equals(Battlegrounds.playerBattleGround[x - 1][y - 1], Const.SHIP_SYMBOL)) {
                System.out.print("You can't place two ships at the same coordinate!");
            }

            Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.SHIP_SYMBOL;
            i++;
        }
    }

    private static void deployComputerBattleShips()
    {
        Common.addEmptyLine();
        System.out.printf("Deploying %d ships on the computer battlefield...", BattleShips.computerShips);

        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;
            int y = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;

            if (Objects.equals(Battlegrounds.computerBattleGround[x-1][y-1], Const.EMPTY_FIELD_SYMBOL)) {
                Battlegrounds.computerBattleGround[x-1][y-1] = Const.SHIP_SYMBOL;
                System.out.println(i + ". battleship deployed");
                i++;
            }
        }
    }
}
