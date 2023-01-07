public class BattleShips
{
    public static int playerShips = 6;
    public static int computerShips = 6;

    public static void main(String[] args)
    {
        Battlegrounds.createBattlegrounds();
        Battlegrounds.deployBattleShips();
        Battlegrounds.printBattlegrounds();

        do {
            Battle.battle();
        } while (BattleShips.playerShips != 0 && BattleShips.computerShips != 0);

        printGameResult();
    }

    private static void printGameResult()
    {
        if (BattleShips.computerShips == 0) {
            Common.addEmptyLine();
            System.out.print("Congratulations, you won!");
        }

        if (BattleShips.playerShips == 0) {
            Common.addEmptyLine();
            System.out.print("Sorry, you lost. Better luck next time!");
        }

        if (BattleShips.playerShips == 0 && BattleShips.computerShips == 0) {
            Common.addEmptyLine();
            System.out.print("REMIS!");
        }
    }
}
