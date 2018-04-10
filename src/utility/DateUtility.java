package utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DateUtility {
    private static final List<String> dateList = new ArrayList<>();
    static {
        dateList.add("2018. 01. 07");
        dateList.add("2018. 01. 14");
        dateList.add("2018. 01. 21");
        dateList.add("2018. 01. 28");

        dateList.add("2018. 02. 04");
        dateList.add("2018. 02. 11");
        dateList.add("2018. 02. 18");
        dateList.add("2018. 02. 25");

        dateList.add("2018. 03. 04");
        dateList.add("2018. 03. 11");
        dateList.add("2018. 03. 18");
        dateList.add("2018. 03. 25");

        dateList.add("2018. 04. 01");
        dateList.add("2018. 04. 08");
        dateList.add("2018. 04. 15");
        dateList.add("2018. 04. 22");
        dateList.add("2018. 04. 29");

        dateList.add("2018. 05. 06");
        dateList.add("2018. 05. 13");
        dateList.add("2018. 05. 20");
        dateList.add("2018. 05. 27");

        dateList.add("2018. 06. 03");
        dateList.add("2018. 06. 10");
        dateList.add("2018. 06. 17");
        dateList.add("2018. 06. 24");

        dateList.add("2018. 07. 01");
        dateList.add("2018. 07. 08");
        dateList.add("2018. 07. 15");
        dateList.add("2018. 07. 22");
        dateList.add("2018. 07. 29");

        dateList.add("2018. 08. 05");
        dateList.add("2018. 08. 12");
        dateList.add("2018. 08. 19");
        dateList.add("2018. 08. 26");

        dateList.add("2018. 09. 02");
        dateList.add("2018. 09. 09");
        dateList.add("2018. 09. 16");
        dateList.add("2018. 09. 23");
        dateList.add("2018. 09. 30");

        dateList.add("2018. 10. 07");
        dateList.add("2018. 10. 14");
        dateList.add("2018. 10. 21");
        dateList.add("2018. 10. 28");

        dateList.add("2018. 11. 04");
        dateList.add("2018. 11. 11");
        dateList.add("2018. 11. 18");
        dateList.add("2018. 11. 25");

        dateList.add("2018. 12. 02");
        dateList.add("2018. 12. 09");
        dateList.add("2018. 12. 16");
        dateList.add("2018. 12. 23");
        dateList.add("2018. 12. 30");
    }

    public static Stream<String> stream() {
        return dateList.stream();
    }

    public static int size() {
        return dateList.size();
    }
}
