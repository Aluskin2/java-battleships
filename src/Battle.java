import java.util.Objects;
import java.util.Random;

public class Battle
{
    private static final Random random = new Random();
    private static boolean retryAttack = true;

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

        Common.addEmptyLine();
        System.out.println("Twoja kolej na atak! podaj koordynaty!");

        do {
            x = Validator.getValidCoordinate("X");
            y = Validator.getValidCoordinate("Y");

            if (isAlreadyAttackedByPlayer(x, y)) {
                retryAttack = true;
            }

            String result = Objects.requireNonNull(getAttackResult(x, y, Const.PLAYER));

            if (isMiss(result)) {
                Battlegrounds.playerShotsGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                retryAttack = false;
            }

            if (isHit(result)) {
                Battlegrounds.computerBattleGround[x - 1][y - 1] = Const.EMPTY_FIELD_SYMBOL;
                Battlegrounds.playerShotsGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                --BattleShips.computerShips;
                retryAttack = false;
            }
        } while (retryAttack);
    }

    private static void computerAttack()
    {
        Common.addEmptyLine();
        System.out.print("Kolej na atak komputera...");

        do {
            int x = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;
            int y = random.nextInt(Const.BATTLEGROUND_SIZE - 1) + 1;

            String result = Objects.requireNonNull(getAttackResult(x, y, Const.COMPUTER));

            if (isMiss(result)) {
                Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                retryAttack = false;
            }

            if (isAlreadyAttackedByComputer(result)) {
                retryAttack = true;
            }

            if (isHit(result)) {
                Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.SHOT_SYMBOL;
                --BattleShips.playerShips;
                retryAttack = false;
            }

        } while (retryAttack);
    }

    private static String getAttackResult(int xAxis, int yAxis, String player)
    {
        switch (player) {
            case Const.PLAYER -> {
                return Battlegrounds.computerBattleGround[xAxis - 1][yAxis - 1];
            }
            case Const.COMPUTER -> {
                return Battlegrounds.playerBattleGround[xAxis - 1][yAxis - 1];
            }
        }

        return null;
    }

    private static boolean isHit(String attackResult)
    {
        if (Objects.equals(attackResult, Const.SHIP_SYMBOL)) {
            System.out.println("Trafiony, zatopiony!");

            return true;
        }

        return false;
    }

    private static boolean isMiss(String attackResult)
    {
        if (Objects.equals(attackResult, Const.EMPTY_FIELD_SYMBOL)) {
            Common.addEmptyLine();
            System.out.print("Pudło!");

            return true;
        }

        return false;
    }

    private static boolean isAlreadyAttackedByComputer(String attackResult)
    {
        return Objects.equals(attackResult, Const.SHOT_SYMBOL);
    }

    public static boolean isAlreadyAttackedByPlayer(int xAxis, int yAxis)
    {
        if (Objects.equals(Battlegrounds.playerShotsGround[xAxis - 1][yAxis - 1], Const.SHOT_SYMBOL)) {
            Common.addEmptyLine();
            System.out.print("Już wykonywałeś atak na te koordynaty, spróbuj ponownie!");

            return true;
        }

        return false;
    }
}
