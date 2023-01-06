public class BattleShips
{
    public static int playerShips = 6;
    public static int computerShips = 6;

    public static void main(String[] args)
    {
        Battlegrounds.createBattlegrounds();

        Battlegrounds.deployPlayerBattleShips();
        Battlegrounds.deployComputerBattleShips();

        Battlegrounds.printBattlegrounds();

        do {
            Battle.battle();
        } while (BattleShips.playerShips != 0 && BattleShips.computerShips != 0);

        endGame();
    }

    public static void endGame()
    {
        if (BattleShips.computerShips == 0) {
            Common.addEmptyLine();
            System.out.print("Gratulacje, wygrałeś!");
        }

        if (BattleShips.playerShips == 0) {
            Common.addEmptyLine();
            System.out.print("Przykro mi, przegrałeś. Spróbuj ponownie!");
        }
    }
}
