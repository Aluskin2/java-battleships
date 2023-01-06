import java.util.Scanner;

public class Validator
{
    private static final Scanner input = new Scanner(System.in);

    public static int getValidCoordinate(String axis)
    {
        int coordinate;

        do {
            System.out.printf("Podaj wartość dla osi %s :", axis);
            while (!input.hasNextInt()) {
                System.out.print("podałeś błędny numer, spróbuj ponownie!");
                input.next();
            }

            coordinate = input.nextInt();

            if (coordinate > Const.BATTLEGROUND_SIZE || coordinate <= 0) {
                System.out.print("Wybrałeś wartość wykraczającą poza wymiary pola bitwy!");
                Common.addEmptyLine();
            }
        } while (coordinate <= 0 || coordinate > Const.BATTLEGROUND_SIZE);

        return coordinate;
    }
}
