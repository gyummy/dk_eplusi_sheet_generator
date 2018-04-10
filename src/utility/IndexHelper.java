package utility;

/**
 * Created by Gyummy on 2018-04-10.
 *
 */
public class IndexHelper {
    private static final int BASE = 26;
    public static String convertColumn(int col) {
        return getAntilogarithm(col - 1);
    }

    private static String getAntilogarithm(int i) {
        int div = i / BASE;
        int mod = i % BASE;
        char c = (char) ('A' + mod);
        String s = String.valueOf(c);

        if(div > 0)
            s = getAntilogarithm(div - 1) + s;
        return s;
    }

    public static int convertColumn(String col) {
        int converted = 0;
        int exp = col.length() - 1;
        for(int i = 0; i < col.length(); i++)
            converted += (int) Math.pow(BASE, exp--) * (col.charAt(i) - 'A' + 1);
        return converted;
    }
}
