import java.util.Arrays;

public class Battlegrounds 
{
    public static final String[][] playerBattleGround = new String[Const.BATTLEGROUND_SIZE][Const.BATTLEGROUND_SIZE];
    public static final String[][] playerShotsGround = new String[Const.BATTLEGROUND_SIZE][Const.BATTLEGROUND_SIZE];
    public static final String[][] computerBattleGround = new String[Const.BATTLEGROUND_SIZE][Const.BATTLEGROUND_SIZE];

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
        Common.EmptyLine();
        System.out.print("COMPUTER SHOOTS / PLAYER FIELD");
        printBattleground(playerBattleGround);

        Common.EmptyLine();
        System.out.print("PLAYER SHOOTS / COMPUTER FIELD");
        printBattleground(playerShotsGround);

        Common.EmptyLine();
        System.out.printf("Your ships: %s | Computer ships: %s", BattleShips.playerShips, BattleShips.computerShips);
    }

    private static void printBattleground(String[][] battleground)
    {
        Common.EmptyLine();
        printBattlegroundHeaderOrFooter();

        Common.EmptyLine();
        printBattlegroundBody(battleground);

        printBattlegroundHeaderOrFooter();
        Common.EmptyLine();
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
}
