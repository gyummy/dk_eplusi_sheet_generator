package sheet;

import org.Town;
import utility.IndexHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Gyummy on 2018-04-09.
 *
 */
public class TempSheetBuilder {

    private static final String HEADER_COMMON = "타임스탬프\t키\t순장선택\t";
    private static final int MIN_ROW = 2;
    private static final int MAX_ROW = 300;
    private static final int REPORT_START_COL = 26 * 4 + 1; //DA
    private static final int REPORT_COL_LENGTH = 5;

    private static String buildKeyFormula(int row) {
        return "=LEFT(" + IndexHelper.convertColumn(REPORT_START_COL + REPORT_COL_LENGTH) + row + ",12)&C" + row;
    }

    private static String buildHeaderRow(Town town) {
        StringBuilder builder = new StringBuilder(HEADER_COMMON);
        AtomicInteger col = new AtomicInteger(4);
        town.getCellList().forEach(cell -> cell.getMemberList().forEach(member -> {
            builder.append("=mid(").append(town.getImportRange(1, member.getRawColumn())).append(",5,").append(member.toString().length()).append(")\t");
            member.setTempColumn(IndexHelper.convertColumn(col.intValue()));
            col.incrementAndGet();
        }));
        while(col.intValue() < REPORT_START_COL) {
            builder.append("\t");
            col.incrementAndGet();
        }
        int townReportStartCol = IndexHelper.convertColumn(town.getReportStartColumn());
        while(col.intValue() < REPORT_START_COL + REPORT_COL_LENGTH) {
            builder.append("=").append(town.getImportRange(1, IndexHelper.convertColumn( townReportStartCol + col.intValue() - REPORT_START_COL)));
            if(col.intValue() < REPORT_START_COL + REPORT_COL_LENGTH - 1)
                builder.append("\t");
            col.incrementAndGet();
        }
        return builder.toString();
    }

    private static String buildBodyRow(Town town, int row){
        StringBuilder builder = new StringBuilder();
        builder.append("=").append(town.getImportRange(row, IndexHelper.convertColumn(1))).append("\t")
                .append(buildKeyFormula(MAX_ROW + MIN_ROW - row)).append("\t")
        .append("=").append(town.getImportRange(row, IndexHelper.convertColumn(2))).append("\t");
        AtomicInteger cnt = new AtomicInteger(4);
        town.getCellList().forEach(cell -> cell.getMemberList().forEach(member -> {
            builder.append("=").append(town.getImportRange(row, member.getRawColumn())).append("\t");
            cnt.incrementAndGet();
        }));
        while(cnt.intValue() < REPORT_START_COL) {
            builder.append("\t");
            cnt.incrementAndGet();
        }
        int townReportStartCol = IndexHelper.convertColumn(town.getReportStartColumn());
        while(cnt.intValue() < REPORT_START_COL + REPORT_COL_LENGTH) {
            builder.append("=").append(town.getImportRange(row, IndexHelper.convertColumn(townReportStartCol + cnt.intValue() - REPORT_START_COL)));
            if(cnt.intValue() < REPORT_START_COL + REPORT_COL_LENGTH - 1)
                builder.append("\t");
            cnt.incrementAndGet();
        }
        return builder.toString();
    }

    public static String build(Town town) {
        StringBuilder builder = new StringBuilder();
        builder.append(buildHeaderRow(town)).append("\n");
        for(int row = MAX_ROW; row >= MIN_ROW; row--) {
            builder.append(buildBodyRow(town, row));
            if(row > MIN_ROW)
                builder.append("\n");
        }
        return builder.toString();
    }
}
