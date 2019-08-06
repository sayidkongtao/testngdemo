package utils;

public class Utils {

    public static void sleep(int s) {
        try {
            Thread.sleep(s * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
