import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(GetSHA1Hash("1123"));
    }

    public static String GetSHA1Hash (String message) {
        String h0 = "0x67452301";
        String h1 = "0xEFCDAB89";
        String h2 = "0x98BADCFE";
        String h3 = "0x10325476";
        String h4 = "0xC3D2E1F0";
        long ml = message.length() / 2;
        for (int i = 0; i < ml / 512 + 1; i++) {
            BigInteger[] w = new BigInteger[80];
            for (int j = 0; j < 16; j++) {
                w[j] = HexIntoBE(message.substring(i*512+j*16, i*512+j*16+15));
            }
            for (int j = 16; j < 80; j++) {
                w[j] = w[j-3].xor(w[j-8].xor(w[j-14].xor(w[j-16])));
            }
            String a = h0;
            String b = h1;
            String c = h2;
            String d = h3;
            String e = h4;
            String f;
            String k;
            for (int j = 0; j < 80; j++) {
                if (j < 20) {
                    f = BEIntoHex(HexIntoBE(b).and(HexIntoBE(c)).or(HexIntoBE(b).and(HexIntoBE(d))).or(HexIntoBE(c).and(HexIntoBE(d))));
                    k = "0x5A827999";
                }
                else if (j < 40) {
                    f = BEIntoHex(HexIntoBE(b).xor(HexIntoBE(c)).xor(HexIntoBE(d)));
                    k = "0x6ED9EBA1";
                }
                else if (j < 60) {
                    f = BEIntoHex(HexIntoBE(b).and(HexIntoBE(c)).or(HexIntoBE(b).and(HexIntoBE(d))).or(HexIntoBE(c).and(HexIntoBE(d))));
                    k = "0x8F1BBCDC";
                }
                else {
                    f = BEIntoHex(HexIntoBE(b).xor(HexIntoBE(c)).xor(HexIntoBE(d)));
                    k = "0xCA62C1D6";
                }
                String temp = BEIntoHex(HexIntoBE(LeftRot(a, 5)).add(HexIntoBE(f)).add(HexIntoBE(e)).add(HexIntoBE(k)).add(w[j]));
                e = d;
                d = c;
                c = LeftRot(b, 30);
                b = a;
                a = temp;
            }
            h0 = BEIntoHex(HexIntoBE(a).add(HexIntoBE(h0)));
            h1 = BEIntoHex(HexIntoBE(b).add(HexIntoBE(h1)));
            h2 = BEIntoHex(HexIntoBE(c).add(HexIntoBE(h2)));
            h3 = BEIntoHex(HexIntoBE(d).add(HexIntoBE(h3)));
            h4 = BEIntoHex(HexIntoBE(e).add(HexIntoBE(h4)));
        }
        return BEIntoHex((HexIntoBE(h0).shiftLeft(128)).or(HexIntoBE(h1).shiftLeft(96)).or(HexIntoBE(h2).shiftLeft(64)).or(HexIntoBE(h3).shiftLeft(32)).or(HexIntoBE(h4)));
    }

    public static String LeftRot(String hex, int num) {
        return ("0x" + hex.substring(2+num)+ hex.substring(2,2 + num));
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