import org.OrgManager;
import sheet.RollBookSheetBuilder;
import sheet.RollInfoSheetBuilder;
import sheet.TempSheetBuilder;
import utility.FileUtility;

import java.io.IOException;

public class App {
    private static final String INPUT_ROOT_PATH = "input";
    private static final String OUTPUT_ROOT_PATH = "output";


    public static void main(String[] argv) {
        OrgManager.build(INPUT_ROOT_PATH + "/metaInfo");
        String tempSheetFileDir = OUTPUT_ROOT_PATH + "/tempSheet";
        String rollBookSheetFileDir = OUTPUT_ROOT_PATH + "/rollBookSheet";
        String rollInfoSheetFileDir = OUTPUT_ROOT_PATH + "/rollInfoSheet";
        OrgManager.getTownSet().forEach(town -> {
            FileUtility.checkDirectory(tempSheetFileDir);
            FileUtility.checkDirectory(rollBookSheetFileDir);
            FileUtility.checkDirectory(rollInfoSheetFileDir);
            try {
                String bufSheetFilePath = tempSheetFileDir + "/" + town.toString() + "_tempSheet";
                FileUtility.write(bufSheetFilePath, TempSheetBuilder.build(town));
                System.out.println("A file for the temp sheet of town '" + town + "' is printed out: " + bufSheetFilePath);

                String rollBookSheetFilePath = rollBookSheetFileDir + "/" + town.toString() + "_rollBookSheet";
                FileUtility.write(rollBookSheetFilePath, RollBookSheetBuilder.build(town));
                System.out.println("A file for the rook book sheet of town '" + town + "' is printed out: " + rollBookSheetFilePath);

                String rollInfoSheetFilePath = rollInfoSheetFileDir + "/" + town.toString() + "_rollInfoSheet";
                FileUtility.write(rollInfoSheetFilePath, RollInfoSheetBuilder.build(town));
                System.out.println("A file for the rook book sheet of town '" + town + "' is printed out: " + rollInfoSheetFilePath);
            } catch (IOException e) {
                System.err.println("An error is occurred while writing a fie: " + e.getMessage());
            }
        });
    }


//        utility.FileUtility.checkDirectory("before");
//        utility.FileUtility.checkDirectory("after");
//
//        String url = "https://docs.google.com/spreadsheets/d/1CFg22k-huGZaJK4O3rUkBbHJj1VyatFfAQw1qXPfoEM/edit";
//        String sheetName = "1_박동은_대표순장";
//        String fileName = "buf_" + sheetName;
//        try {
//            String buf = convert(url, sheetName, utility.FileUtility.read("before/" + fileName));
//            utility.FileUtility.write("after/" + fileName, buf);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private static String convert(String url, String sheetName, String original) {
//        StringBuilder sb = new StringBuilder();
//        List<String> splitByTap = Arrays.asList(original.split("\\t"));
//        List<String> split = new ArrayList<>();
//        for(String s : splitByTap) {
//            int newLineIdx = s.indexOf("\r\n");
//            if(newLineIdx > -1) {
//                split.add(s.substring(0, newLineIdx + 2));
//                split.add(s.substring(newLineIdx + 2));
//            } else {
//                split.add(s);
//            }
//        }
//        for(String s : split) {
//            if (s.contains(sheetName)) {
//                int start, end;
//                if(s.indexOf(sheetName) == 0)
//                    s = "'" + s;
//                start = s.indexOf(sheetName) - 1;
//                end = s.indexOf(",", start);
//                if(end == -1)
//                    end = s.length();
//
//                String ref = s.substring(start, end);
//                if(ref.contains("\r\n"))
//                    s = s.replace(ref, "IMPORTRANGE(\"" + url + "\", \"" + ref.substring(0, s.indexOf("\r\n")) + "\")\r\n");
//                else
//                    s = s.replace(ref, "IMPORTRANGE(\"" + url + "\", \"" + ref + "\")");
//                sb.append("=");
//            } else if (s.startsWith("left")) {
//                s = "=" + s;
//            }
//            sb.append(s);
//            if(!s.contains("\n"))
//                sb.append("\t");
//        }
//        return sb.toString();
//    }
}
