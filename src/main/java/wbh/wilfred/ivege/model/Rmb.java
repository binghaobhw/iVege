package wbh.wilfred.ivege.model;

import java.math.BigDecimal;

public class Rmb implements Comparable<Rmb> {
    private long jiao;

    public static final Rmb ZERO = new Rmb("0");

    public Rmb() {}

    public Rmb(long jiao) {
        this.jiao = jiao;
    }

    public Rmb(String rmb) {
        if (rmb == null || rmb.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] splitResult = rmb.split("\\.");
        switch (splitResult.length) {
            case 1:
                jiao = 10 * Long.parseLong(splitResult[0]);
                break;
            case 2:
                jiao = Long.parseLong(splitResult[0].concat
                        (splitResult[1].substring(0, 1)));
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public long getJiao() {
        return jiao;
    }

    public Rmb plus(Rmb addend) {
        return new Rmb(jiao + addend.getJiao());
    }

    public Rmb minus(Rmb subtrahend) {
        return new Rmb(jiao - subtrahend.getJiao());
    }

    public Rmb times(Rmb multiplier) {
        return new Rmb(jiao * multiplier.getJiao());
    }

    public Rmb times(BigDecimal multiplier) {
        int scale = multiplier.scale();
        long product = jiao * (multiplier.movePointRight(scale).longValue());
        return new Rmb(dividedByScaleAndRound(product, scale));
    }

    public static long dividedByScaleAndRound(long val, int scale) {
        if (val == 0L) {
            return val;
        }
        String s = Long.toString(val);
        int tenthPosition = s.length() - scale;
        long result = Long.parseLong(s.substring(0, tenthPosition));
        if (s.charAt(tenthPosition) > '4') {
            ++result;
        }
        return result;
    }

    public Rmb dividedBy(Rmb divisor) {
        return new Rmb(jiao / divisor.getJiao());
    }

    public Rmb dividedBy(BigDecimal divisor) {
        int scale = divisor.scale();
        int a = 1;
        for (int i = 0; i < scale + 1; ++i) {
            a *= 10;
        }
        long quotient = jiao * a / (divisor.movePointRight(scale).longValue());
        return new Rmb(dividedByScaleAndRound(quotient, 1));
    }

    public int compareTo(Rmb rmb) {
        long d = jiao - rmb.getJiao();
        if (d > 0) {
            return 1;
        } else if (d < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean greaterThan(Rmb rmb) {
        return compareTo(rmb) > 0;
    }

    public boolean greaterOrEqual(Rmb rmb) {
        return compareTo(rmb) >= 0;
    }

    public boolean equalTo(Rmb rmb) {
        return compareTo(rmb) == 0;
    }

    public boolean lessThan(Rmb rmb) {
        return compareTo(rmb) < 0;
    }

    public boolean lessOrEqual(Rmb rmb) {
        return compareTo(rmb) <= 0;
    }

    public String toString() {
        return String.valueOf(jiao / 10) + '.' + jiao % 10;
    }
}
