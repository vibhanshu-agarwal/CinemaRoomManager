import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String[] elements = scanner.nextLine().split(" ");
        int number = scanner.nextInt();

        int sum = 0;
        for (String element : elements) {
            int value = Integer.parseInt(element);
            if (value > number) {
                sum += value;
            }
        }
        System.out.println(sum);

    }
}