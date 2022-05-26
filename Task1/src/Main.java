import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        var vector = in.next();

        BigInteger bit_size;
        bit_size = new BigInteger(vector);
        var logarithm = Math.log(bit_size.intValue()) / Math.log(2);
        if (logarithm == Math.round(logarithm)) {
            System.out.println(Pow(BigInteger.TWO, bit_size));
            BigInteger rand = GenerateRandomNum(bit_size.intValue());
            System.out.println(rand);
            System.out.println(FindElementTime(rand));
        } else {
            System.out.println("Incorrect input");
        }
    }

    public static BigInteger GenerateRandomNum(int size) {
        return new BigInteger(size, new Random());
    }

    public static BigInteger Pow(BigInteger base, BigInteger exponent) {
        return base.pow(exponent.intValue());
    }

    public static long FindElementTime(BigInteger value) {
        long start_time = System.currentTimeMillis();
        BigInteger current_value = BigInteger.ZERO;
        while (!current_value.equals(value)) {
            current_value = current_value.add(BigInteger.ONE);
        }
        return System.currentTimeMillis() - start_time;
    }
}