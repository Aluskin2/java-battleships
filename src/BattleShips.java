import java.util.Arrays;

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

        //TODO spięcie pozostałych metod wewnętrz
    }

    public static void createBattlegrounds()
    {
        System.out.print("PLAYER BATTLEGROUND");
        System.out.print("  ");

        createBattleground(playerBattleGround);
        printBattleground(playerBattleGround);
        System.out.println();

        System.out.print("PLAYER SHOTS / ENEMY BATTLEGROUND");
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

    public static void fillPlayerBattleground()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/3
    }

    public static void fillComputerBattleground()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/6
    }

    public static void playerAttack()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/4
    }

    public static void computerAttack()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/5
    }

    public static void endGame()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/8
    }
}
