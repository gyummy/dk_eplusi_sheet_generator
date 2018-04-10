package sheet;

import org.Town;
import utility.DateUtility;
import utility.IndexHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Gyummy on 2018-04-09.
 *
 */
public class RollInfoSheetBuilder {

    private static String headerRow;
    private static final int MIN_ROW = 2;
    private static final String[] STATUS_TYPES = {"예배+순모임", "예배만", "순모임만", "불참"};

    static {
        headerRow = "순\t출석\t";
        DateUtility.stream().forEach(date -> headerRow += date + "\t");
    }

    private static String buildBodyRow(Town town){
        AtomicInteger row = new AtomicInteger(MIN_ROW);
        StringBuilder builder = new StringBuilder();
        town.getCellList().forEach(cell -> {
            for(String statusType : STATUS_TYPES) {
                builder.append(cell.getName()).append("\t")
                        .append(statusType).append("\t");
                for(int col = 3; col < DateUtility.size(); col++) {
                    builder.append("=COUNTIFS('출석부'!$B$").append(row).append(":$B$").append(town.size())
                            .append(",$A").append(row)
                            .append(",'출석부'!").append(IndexHelper.convertColumn(col + 1)).append("$").append(row).append(":").append(IndexHelper.convertColumn(col + 1)).append("$").append(town.size())
                            .append(",$B").append(row).append(")");
                    if(col < DateUtility.size() - 1)
                        builder.append("\t");
                    else if(row.intValue() < town.size() - 1)
                        builder.append("\n");
                }
            }
            row.incrementAndGet();
        });
        return builder.toString();
    }

    public static String build(Town town) {
        return headerRow + "\n" + buildBodyRow(town);
    }
}