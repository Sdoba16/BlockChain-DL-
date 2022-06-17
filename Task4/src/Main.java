import java.math.BigInteger;
import java.util.Random;

public class Main {
    static BigInteger n;
    static BigInteger e;
    static BigInteger d;

    public static void main(String[] args) {
        KeyGen();
        BigInteger message = new BigInteger("216736466374734384735774757478578784577457745848587475726487632");
        System.out.println(Decrypt(Encrypt(message)));
    }


    public static void KeyGen() {
        BigInteger p = GenerateRandomNum(2000);
        BigInteger q = GenerateRandomNum(2000);
        p = p.nextProbablePrime();
        q = q.nextProbablePrime();
        n = p.multiply(q);
        e = BigInteger.valueOf(3);
        BigInteger fi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        while (!(e.gcd(fi).equals(BigInteger.ONE))) {
            e = e.add(BigInteger.TWO);
        }
        BigInteger k = BigInteger.ONE;
        while (!(((k.multiply(fi)).add(BigInteger.ONE)).mod(e).equals(BigInteger.ZERO))) k = k.add(BigInteger.ONE);
        d = ((k.multiply(fi)).add(BigInteger.ONE)).divide(e);
    }

    public static BigInteger Encrypt (BigInteger message) {
        return message.modPow(e, n);
    }

    public static BigInteger Decrypt (BigInteger cipher_text) {
        return cipher_text.modPow(d, n);
    }

    public static BigInteger GenerateRandomNum(int size) {
        BigInteger r = new BigInteger(size, new Random());
        while (r.compareTo(BigInteger.TWO) <= 0) r = new BigInteger(size, new Random());
        return new BigInteger(size, new Random());
    }
}