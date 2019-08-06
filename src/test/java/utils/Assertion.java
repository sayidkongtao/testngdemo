package utils;

import org.apache.log4j.Logger;
import org.testng.Assert;

public class Assertion {
    private static Logger logger = Logger.getLogger(Assertion.class);

    public static void assertEquals(String actual, String expected) {
        actual = actual.trim();
        expected = expected.trim();
        logger.info("实际值为：" + actual);
        logger.info("期望值为：" + expected);
        Assert.assertEquals(actual, expected);
    }

    public static void assertNotEquals(String actual, String expected) {
        actual = actual.trim();
        expected = expected.trim();
        logger.info("实际值为：" + actual);
        logger.info("期望值为：" + expected);
        Assert.assertNotEquals(actual, expected);
    }

    public static void assertEquals(int actual, int expected) {
        logger.info("实际值为：" + actual);
        logger.info("期望值为：" + expected);
        Assert.assertEquals(actual, expected);
    }

    public static void assertEquals(boolean actual, boolean expected) {
        logger.info("实际值：" + actual);
        logger.info("期望值:" + expected);
        Assert.assertEquals(actual, expected);
    }

    public static void contains(String actual, String expected) {
        logger.info("实际值：" + actual);
        logger.info("期望值:" + expected);
        actual.contains(expected);
    }
}
