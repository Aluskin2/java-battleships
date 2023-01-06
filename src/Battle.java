import java.util.Objects;
import java.util.Random;

public class Battle
{
    private static final Random random = new Random();

    public static void battle()
    {
        playerAttack();
        computerAttack();

        Battlegrounds.printBattlegrounds();
    }

    private static void playerAttack()
    {
        int x;
        int y;
        boolean retryAttack = true;

        Common.addEmptyLine();
        System.out.println("Twoja kolej na atak! podaj koordynaty!");

        do {
            x = Validator.getValidCoordinate("X");
            y = Validator.getValidCoordinate("Y");

            if (Objects.equals(Battlegrounds.playerShotsGround[x - 1][y - 1], Const.SHOT_SYMBOL)) {
                Common.addEmptyLine();
                System.out.print("Już wykonywałeś atak na te koordynaty, spróbuj ponownie!");
                Common.addEmptyLine();
                continue;
            }

            switch (Battlegrounds.computerBattleGround[x - 1][y - 1]) {
                case Const.SHIP_SYMBOL -> {
                    Common.addEmptyLine();
                    System.out.print("Brawo, zatopiłeś statek komputera!");

                    Battlegrounds.computerBattleGround[x - 1][y - 1] = Const.EMPTY_FIELD_SYMBOL;
                    Battlegrounds.playerShotsGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                    --BattleShips.computerShips;
                    retryAttack = false;
                }
                case Const.EMPTY_FIELD_SYMBOL -> {
                    Common.addEmptyLine();
                    System.out.print("Niestety, spudłowałeś");

                    Battlegrounds.playerShotsGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                    retryAttack = false;
                }
            }
        } while (retryAttack);
    }

    private static void computerAttack()
    {
        boolean retryAttack = true;

        Common.addEmptyLine();
        System.out.print("Kolej na atak komputera...");

        do {
            int x = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;
            int y = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;

            switch (Battlegrounds.computerBattleGround[x - 1][y - 1]) {
                case Const.SHIP_SYMBOL -> {
                    System.out.println("Ups, komputer zatopił Twój statek!");

                    Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                    --BattleShips.playerShips;
                    retryAttack = false;
                }
                case Const.EMPTY_FIELD_SYMBOL -> {
                    System.out.println("komputer spudłował!");

                    Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                    retryAttack = false;
                }
                case Const.SHOT_SYMBOL -> { // jeśli komputer zaatakował to samo miejsce, powtarzamy atak
                    System.out.println("komputer zaatkował to samo miejsce co wcześniej!, jeszcze raz...");
                }
            }
        } while (retryAttack);
    }
}
