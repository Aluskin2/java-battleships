import java.util.Scanner;

public class Validator
{
    private static final Scanner input = new Scanner(System.in);

    public static int getValidCoordinate(String axis)
    {
        int coordinate;

        do {
            System.out.printf("Choose value for axis %s :", axis);
            while (!input.hasNextInt()) {
                System.out.print("it's not a valid number!, try again!");
                input.next();
            }

            coordinate = input.nextInt();

            if (coordinate > Const.BATTLEGROUND_SIZE || coordinate <= 0) {
                System.out.print("You choose a value bigger than battleground size!");
                Common.addEmptyLine();
            }
        } while (coordinate <= 0 || coordinate > Const.BATTLEGROUND_SIZE);

        return coordinate;
    }
}
