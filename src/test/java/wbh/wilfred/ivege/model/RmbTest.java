package wbh.wilfred.ivege.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RmbTest {

    @Test(expected = IllegalArgumentException.class)
    public void testRmb() throws Exception {
        new Rmb("++11.111-");
    }

    @Test
    public void testPlus() throws Exception {
        Rmb a = new Rmb("11.08");
        Rmb b = new Rmb("22.5");
        assertTrue(a.plus(b).equals(new Rmb("33.5")));
    }

    @Test
    public void testMinus() throws Exception {
        Rmb a = new Rmb("22.5");
        Rmb b = new Rmb("3.0");
        assertTrue(a.minus(b).equals(new Rmb("19.5")));
    }

    @Test
    public void testTimes() throws Exception {
        assertTrue(new Rmb("1.5").times(new BigDecimal("1.5")).equals(new
                Rmb("2.3")));
        assertTrue(new Rmb("1.9").times(new BigDecimal("1.9")).equals(new
                Rmb("3.6")));
        assertTrue(new Rmb("10.5").times(new BigDecimal("0.85")).equals(new
                Rmb("8.9")));
    }

    @Test
    public void testDividedBy() throws Exception {
        assertTrue(new Rmb("2.2").dividedBy(new BigDecimal("1.5")).equals
                (new Rmb("1.5")));
        assertTrue(new Rmb("4.3").dividedBy(new BigDecimal("0.85")).equals
                (new Rmb("5.1")));
    }

    @Test
    public void testToString() throws Exception {
        assertTrue("111.2".equals(new Rmb("111.2").toString()));
        assertTrue("111.0".equals(new Rmb("111").toString()));
    }

    @Test
    public void testDividedByTenAndRound() throws Exception {
        long a = 11114;
        assertEquals(1111L, Rmb.dividedByScaleAndRound(a, 1));
        ++a;
        assertEquals(1112L, Rmb.dividedByScaleAndRound(a, 1));
    }
}