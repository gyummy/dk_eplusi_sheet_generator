import java.util.*;

public class App {
    private static final String ROOT_PATH = "output";
    private static final String[] PATHS = {
            ROOT_PATH + "/동아리",
            ROOT_PATH + "/봉봉",
            ROOT_PATH + "/빛진",
            ROOT_PATH + "/사랑",
            ROOT_PATH + "/삼",
            ROOT_PATH + "/세",
            ROOT_PATH + "/지안"
    };


    public static void main(String[] argv) {
        for(String path : PATHS) {
            FileUtility.checkDirectory(path + "/buf");

        }
//        FileUtility.checkDirectory("before");
//        FileUtility.checkDirectory("after");
//
//        String url = "https://docs.google.com/spreadsheets/d/1CFg22k-huGZaJK4O3rUkBbHJj1VyatFfAQw1qXPfoEM/edit";
//        String sheetName = "1_박동은_대표순장";
//        String fileName = "buf_" + sheetName;
//        try {
//            String buf = convert(url, sheetName, FileUtility.read("before/" + fileName));
//            FileUtility.write("after/" + fileName, buf);
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    private static String convert(String url, String sheetName, String original) {
        StringBuilder sb = new StringBuilder();
        List<String> splitByTap = Arrays.asList(original.split("\\t"));
        List<String> split = new ArrayList<>();
        for(String s : splitByTap) {
            int newLineIdx = s.indexOf("\r\n");
            if(newLineIdx > -1) {
                split.add(s.substring(0, newLineIdx + 2));
                split.add(s.substring(newLineIdx + 2));
            } else {
                split.add(s);
            }
        }
        for(String s : split) {
            if (s.contains(sheetName)) {
                int start, end;
                if(s.indexOf(sheetName) == 0)
                    s = "'" + s;
                start = s.indexOf(sheetName) - 1;
                end = s.indexOf(",", start);
                if(end == -1)
                    end = s.length();

                String ref = s.substring(start, end);
                if(ref.contains("\r\n"))
                    s = s.replace(ref, "IMPORTRANGE(\"" + url + "\", \"" + ref.substring(0, s.indexOf("\r\n")) + "\")\r\n");
                else
                    s = s.replace(ref, "IMPORTRANGE(\"" + url + "\", \"" + ref + "\")");
                sb.append("=");
            } else if (s.startsWith("left")) {
                s = "=" + s;
            }
            sb.append(s);
            if(!s.contains("\n"))
                sb.append("\t");
        }
        return sb.toString();
    }

    private static void buildBufSheet() {

    }
}
