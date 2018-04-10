package org;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Gyummy on 2018-04-10.
 *
 */
public class Town {
    private int group;
    private String name;
    private String primeLeaderName;
    private String importRange;
    private List<Cell> cellList;
    private String reportStartColumn;

    public Town(int group, String name, String primeLeaderName, String importRange) {
        this.group = group;
        this.name = name;
        this.primeLeaderName = primeLeaderName;
        this.importRange = importRange;
        cellList = new ArrayList<>();
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimeLeaderName() {
        return primeLeaderName;
    }

    public void setPrimeLeaderName(String primeLeaderName) {
        this.primeLeaderName = primeLeaderName;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void addCell(Cell cell) {
        this.cellList.add(cell);
    }

    public String getImportRange() {
        return importRange;
    }

    public String getImportRange(int row, String col) {
        String index = col + row;
        return importRange.replace("${INDEX}", index);
    }

    public void setImportRange(String importRange) {
        this.importRange = importRange;
    }

    public String getReportStartColumn() {
        return reportStartColumn;
    }

    public void setReportStartColumn(String reportStartColumn) {
        this.reportStartColumn = reportStartColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Town town = (Town) o;

        return getName() != null ? getName().equals(town.getName()) : town.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return name + "마을";
    }
}
