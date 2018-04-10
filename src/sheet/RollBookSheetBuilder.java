package sheet;

        import org.Town;
        import utility.DateUtility;
        import utility.IndexHelper;

        import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Gyummy on 2018-04-09.
 *
 */
public class RollBookSheetBuilder {

    private static String headerRow;
    private static final int MIN_ROW = 2;

    static {
        headerRow = "청년\t순장\t순원\t";
        DateUtility.stream().forEach(date -> headerRow += date + "\t");
    }

    private static String buildBodyRow(Town town){
        AtomicInteger row = new AtomicInteger(MIN_ROW);
        StringBuilder builder = new StringBuilder();
        town.getCellList().forEach(cell -> cell.getMemberList().forEach(member -> {
            builder.append(town.getGroup()).append("청년\t")
                    .append(cell.getName()).append("\t")
                    .append(member).append("\t");
            for(int col = 4; col < DateUtility.size(); col++) {
                builder.append("=IFERROR(HLOOKUP($C").append(row)
                        .append(",temp!").append(cell.getFirstTempColumn()).append(":").append(cell.getLastTempColumn())
                        .append(",MATCH(LEFT(").append(IndexHelper.convertColumn(col)).append("$1,12)&$B").append(row)
                        .append(",temp!B:$B,0),FALSE),\"NS\")");
                if(col < DateUtility.size() - 1)
                    builder.append("\t");
                else if(row.intValue() < town.size() - 1)
                    builder.append("\n");
            }
            row.incrementAndGet();
        }));
        return builder.toString();
    }

    public static String build(Town town) {
        return headerRow + "\n" + buildBodyRow(town);
    }
}
