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

        Common.EmptyLine();
        System.out.println("Your turn to attack! give me your coordinates!");

        do {
            x = Validator.getValidCoordinate("X");
            y = Validator.getValidCoordinate("Y");

            String result = Objects.requireNonNull(getAttackResult(x, y, Const.PLAYER));

            retryAttack = isAlreadyAttackedByPlayer(x,y);

            if (retryAttack) {
                continue;
            }

            if (!Validator.isFieldEmpty(result)) {
                System.out.println("Hit, and sunk!");
                Battlegrounds.computerBattleGround[x - 1][y - 1] = Const.EMPTY_FIELD_SYMBOL;
                --BattleShips.computerShips;
            } else {
                System.out.println("Missed!");
            }

            Battlegrounds.playerShotsGround[x - 1][y - 1] = Const.FIELD_WAS_SHOT_SYMBOL;

        } while (retryAttack);
    }

    private static void computerAttack()
    {
        Common.EmptyLine();
        System.out.print("Computer turn...");

        do {
            int x = random.nextInt(Const.BATTLEGROUND_SIZE) + 1;
            int y = random.nextInt(Const.BATTLEGROUND_SIZE) + 1;

            String result = Objects.requireNonNull(getAttackResult(x, y, Const.COMPUTER));

            retryAttack = Validator.wasFieldShot(result);

            if (retryAttack) {
                continue;
            }

            if (!Validator.isFieldEmpty(result)) {
                System.out.println("Hit, and sunk!");
                --BattleShips.playerShips;
            } else {
                System.out.println("Missed!");
            }

            Battlegrounds.playerBattleGround[x - 1][y - 1] = Const.FIELD_WAS_SHOT_SYMBOL;

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
            default -> {
                return null;
            }
        }
    }

    private static boolean isAlreadyAttackedByPlayer(int xAxis, int yAxis)
    {
        if (Validator.wasFieldShot(Battlegrounds.playerShotsGround[xAxis - 1][yAxis - 1])) {
            Common.EmptyLine();
            System.out.println("You already attacked this field, try again!");

            return true;
        }

        return false;
    }
}
