package org;

import utility.FileUtility;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by Gyummy on 2018-04-10.
 *
 */
public class OrgManager {
    private static final Set<Town> townSet = new HashSet<>();

    public static void build(String metaInfoPath) {
        try {
            String metaInfo = FileUtility.read(metaInfoPath);
            String[] lines = metaInfo.split("\\r\\n");
            Town currentTown = null;
            Cell currentCell = null;
            for(String line : lines) {
                char firstChar = line.charAt(0);
                switch (firstChar) {
                    case 't':
                        if(currentTown != null) {
                            currentTown.addCell(currentCell);
                            townSet.add(currentTown);
                            currentCell = null;
                        }
                        StringTokenizer tst = new StringTokenizer(line.substring(1));
                        int group = Integer.valueOf(tst.nextToken());
                        String townName = tst.nextToken();
                        String primeLeaderName = tst.nextToken();
                        String importRange = tst.nextToken();
                        currentTown = new Town(group, townName, primeLeaderName, importRange);
                        break;
                    case 'c':
                        if(currentTown != null) {
                            if (currentCell != null)
                                currentTown.addCell(currentCell);

                            String leaderName = line.substring(1);
                            currentCell = new Cell(leaderName);
                        }
                        break;
                    case 'm':
                        if(currentCell != null) {
                            StringTokenizer mst = new StringTokenizer(line.substring(1));
                            String name = mst.nextToken();
                            String peer = mst.nextToken();
                            String column = mst.nextToken();
                            currentCell.addMember(new Member(name, peer, column));
                        }
                        break;
                    case 'r':
                        String reportStartColumn = line.substring(1);
                        if(currentTown != null)
                            currentTown.setReportStartColumn(reportStartColumn);
                        break;
                }
            }
            if(currentTown != null && currentCell != null) {
                currentTown.addCell(currentCell);
                townSet.add(currentTown);
            }
        } catch (IOException e) {
            System.err.println("An error is occurred while building OrgManager: " + e.getMessage());
        }
    }

    public static Set<Town> getTownSet() {
        return townSet;
    }

    public static Town getTown(String name) {
        for(Town town : townSet) {
            if(town.getName().equals(name))
                return town;
        }
        return null;
    }
}
