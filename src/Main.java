import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        final InputReader br = new InputReader(System.in);
        final BigInteger t = BigInteger.valueOf(br.readLong()), w = BigInteger.valueOf(br.readLong()), b = BigInteger.valueOf(br.readLong());
        System.out.println(new Solver().solve(t, w, b));
    }
}

class Solver {

    private BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.compareTo(b) < 0) {
            BigInteger temp = a;
            a = b;
            b = temp;
        }
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = a.mod(b);
            a = b;
            b = temp;
        }
        return a;
    }

    public String solve(BigInteger t, BigInteger w, BigInteger b) {
        BigInteger smaller = w.compareTo(b) > 0 ? (b.subtract(BigInteger.ONE)) : (w.subtract(BigInteger.ONE));
        BigInteger tot = smaller;
        BigInteger lcm = w.divide(gcd(w, b)).multiply(b);
        BigInteger added = t.divide(lcm);
        added = added.multiply(smaller.add(BigInteger.ONE));
        BigInteger remainder = t.mod(lcm);
        if (remainder.compareTo(smaller) < 0) {
            added = added.subtract(smaller.subtract(remainder));
        }
        tot = tot.add(added);
        BigInteger gcd = gcd(t, tot);
        tot = tot.divide(gcd);
        t = t.divide(gcd);
        return tot + "/" + t;
    }
}

class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];

    private int curChar;

    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        }
        while (!isSpaceChar(c));
        return res.toString();
    }

    public long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        while (!isSpaceChar(c));
        return res * sgn;
    }


}
