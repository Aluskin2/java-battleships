import java.util.Random;

public class BattleShips
{
    private static final Random random = new Random();
    public static int playerShips = 6;
    public static int computerShips = 6;

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
        Common.EmptyLine();
        System.out.printf("Battleground size: %s/%s", Const.BATTLEGROUND_SIZE, Const.BATTLEGROUND_SIZE);
        Common.EmptyLine();

        for (int i = 1; i <= BattleShips.playerShips;) {
            System.out.printf("Deploy %d ship", i);
            Common.EmptyLine();

            x = Validator.getValidCoordinate("X");
            y = Validator.getValidCoordinate("Y");

            if (Validator.doesFieldContainShip(Battlegrounds.playerBattleGround[x - 1][y - 1])) {
                System.out.print("You can't place two ships at the same coordinate!");
                Common.EmptyLine();
                continue;
            }

            Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.FIELD_HAS_SHIP_SYMBOL;
            i++;
        }
    }

    private static void deployComputerBattleShips()
    {
        Common.EmptyLine();
        System.out.printf("Deploying %d ships on the computer battlefield...", BattleShips.computerShips);
        Common.EmptyLine();

        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = random.nextInt(Const.BATTLEGROUND_SIZE) + 1;
            int y = random.nextInt(Const.BATTLEGROUND_SIZE) + 1;

            if (Validator.isFieldEmpty(Battlegrounds.computerBattleGround[x-1][y-1])) {
                Battlegrounds.computerBattleGround[x-1][y-1] = Const.FIELD_HAS_SHIP_SYMBOL;
                System.out.println(i + ". battleship deployed");
                i++;
            }
        }
    }
}
