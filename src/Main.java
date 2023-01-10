public class Main
{
    public static void main(String[] args)
    {
        Battlegrounds.createBattlegrounds();
        BattleShips.deployBattleShips();
        Battlegrounds.printBattlegrounds();

        do {
            Battle.battle();
        } while (BattleShips.playerShips != 0 && BattleShips.computerShips != 0);

        printGameResult();
    }

    private static void printGameResult()
    {
        if (BattleShips.playerShips == 0 && BattleShips.computerShips == 0) {
            Common.EmptyLine();
            System.out.print("A TIE!");

        } else if (BattleShips.computerShips == 0) {
            Common.EmptyLine();
            System.out.print("Congratulations, you won!");

        } else if (BattleShips.playerShips == 0) {
            Common.EmptyLine();
            System.out.print("Sorry, you lost. Better luck next time!");
        }
    }
}
