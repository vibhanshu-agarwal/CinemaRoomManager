import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] incomes = new int[n];
        for (int i = 0; i < n; i++) {
            incomes[i] = scanner.nextInt();
        }
        int[] taxes = new int[n];
        for (int i = 0; i < n; i++) {
            taxes[i] = scanner.nextInt();
        }
        double[] taxesToPay = new double[n];
        for (int i = 0; i < n; i++) {
            if (incomes[i] == 0) {
                taxesToPay[i] = 0;
            } else {
                taxesToPay[i] = incomes[i] * taxes[i] / 100.0;
            }

        }
        int companyNumMostTaxes = 0;
        for (int i = 0; i < taxesToPay.length; i++) {
            if (taxesToPay[i] > taxesToPay[companyNumMostTaxes]) {
                companyNumMostTaxes = i;
            }
        }
        System.out.println(companyNumMostTaxes + 1);
    }
}