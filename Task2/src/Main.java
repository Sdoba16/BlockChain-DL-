import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BigInteger hex = ReadNum();
        IntIntoHex(hex);
        String k = IntIntoHex(hex);
        System.out.println(k);
    }

    public static String ReadHex() {
        Scanner input = new Scanner(System.in);
        var vector = input.next();
        return vector;
    }

    public static BigInteger ReadNum() {
        Scanner input = new Scanner(System.in);
        var vector = input.next();
        return new BigInteger(vector);
    }

    public static BigInteger HexIntoBE(String hex) {
        BigInteger res = BigInteger.ZERO;
        BigInteger deg = BigInteger.ONE;
        for (int i = hex.length()-1; i > 1; i--) {
            BigInteger symbol = BigInteger.ZERO;
            if (hex.charAt(i) >= 'a' && hex.charAt(i) <= 'f') {
                symbol = BigInteger.valueOf(10).add(BigInteger.valueOf(hex.charAt(i) - 'a'));
            }
            else if (hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F') {
                symbol = BigInteger.valueOf(10).add(BigInteger.valueOf(hex.charAt(i) - 'A'));
            }
            else symbol = symbol.add(BigInteger.valueOf(hex.charAt(i) - '0'));
            res = res.add(symbol.multiply(deg));
            deg = deg.multiply(BigInteger.valueOf(16));
        }
        return res;
    }

    public static BigInteger HexIntoLE(String hex) {
        BigInteger res = BigInteger.ZERO;
        BigInteger deg = BigInteger.ONE;
        for (int j = 2; j < hex.length(); j++) {
            int i = j - 2 * (j % 2) + 1;
            BigInteger symbol = BigInteger.ZERO;
            if (hex.charAt(i) >= 'a' && hex.charAt(i) <= 'f') {
                symbol = BigInteger.valueOf(10).add(BigInteger.valueOf(hex.charAt(i) - 'a'));
            }
            else if (hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F') {
                symbol = BigInteger.valueOf(10).add(BigInteger.valueOf(hex.charAt(i) - 'A'));
            }
            else symbol = symbol.add(BigInteger.valueOf(hex.charAt(i) - '0'));
            res = res.add(symbol.multiply(deg));
            deg = deg.multiply(BigInteger.valueOf(16));
        }
        return res;
    }

    public static String BEIntoHex(BigInteger be) {
        String res = "";
        BigInteger deg = BigInteger.ONE;
        while (!be.equals(BigInteger.ZERO)) {
            BigInteger symbol = BigInteger.ZERO;
            symbol = be.mod(BigInteger.valueOf(16));
            if (symbol.intValue() < 10) {
                res += symbol.toString();
            }
            else {
                res += Character.toString('A' + (symbol.intValue() - 10));
            }
            be = be.divide(BigInteger.valueOf(16));
        }
        res += "x0";
        String fin_res = "";
        for (int i = res.length() - 1; i > -1; i--) {
            fin_res += res.charAt(i);
        }
        return fin_res;
    }

    public static String LEIntoHex(BigInteger le) {
        String res = "";
        BigInteger deg = BigInteger.ONE;
        while (!le.equals(BigInteger.ZERO)) {
            BigInteger symbol = BigInteger.ZERO;
            symbol = le.mod(BigInteger.valueOf(16));
            if (symbol.intValue() < 10) {
                res += symbol.toString();
            }
            else {
                res += Character.toString('A' + (symbol.intValue() - 10));
            }
            le = le.divide(BigInteger.valueOf(16));
        }
        res += "x0";
        String fin_res = "";
        for (int i = res.length() - 1; i > -1; i--) {
            fin_res += res.charAt(i);
        }
        return fin_res;
    }

}