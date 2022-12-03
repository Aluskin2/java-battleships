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
        System.out.println();
        System.out.print("  ");

        createBattleground(playerBattleGround, true);
        System.out.println();

        System.out.print("PLAYER SHOTS / ENEMY BATTLEGROUND");
        System.out.println();
        System.out.print("  ");

        createBattleground(playerShotsGround, true);
        System.out.println();

        createBattleground(computerBattleGround, false);
    }

    private static void createBattleground(String[][] battleground, Boolean print)
    {
        if (print) {
            for (int i = 1; i <= battleGroundColumns; i++) {
                System.out.print(i);
            }
            System.out.println();
        }

        for (int i = 0; i < battleground.length; i++) {
            for (int j = 0; j < battleground[i].length; j++) {
                battleground[i][j] = "O";
                if (print) {
                    if (j == 0) {
                        System.out.print((i + 1) + "|" + battleground[i][j]);
                    } else if (j == battleground[i].length - 1) {
                        System.out.print(battleground[i][j] + "|" + (i + 1));
                    } else {
                        System.out.print(battleground[i][j]);
                    }
                }
            }
            System.out.println();
        }

        if (print) {
            System.out.print("  ");
            for (int i = 1; i <= battleGroundColumns; i++) {
                System.out.print(i);
            }
            System.out.println();
        }
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

    public static void getPlayerBattlegroundView()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/2
    }

    public static void getPlayerShotsView()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/7
    }

    public static void endGame()
    {
        //TODO https://github.com/Aluskin2/java-battleships/issues/8
    }
}
