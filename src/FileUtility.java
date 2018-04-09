import java.io.*;

public class FileUtility {

    private static final int BUFFER_SIZE = 8192;

    public static void checkDirectory(String dirPath) {
        File file = new File(dirPath);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static String read(String filePath) throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            int c;
            while((c = bufferedReader.read()) > 0)
                sb.append((char) c);
        } finally {
            if(fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ignored) {
                }
            }
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return sb.toString();
    }

    public static void write(String filePath, String contents) throws IOException {
        write(filePath, contents, "UTF-8");
    }

    public static void write(String filePath, String contents, String enc) throws IOException {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(filePath);
            osw = new OutputStreamWriter(fos, enc);
            bw = new BufferedWriter(osw);

            for(int i = 0; i < contents.length(); i += BUFFER_SIZE) {
                if (contents.length() - i < BUFFER_SIZE) {
                    bw.append(contents.substring(i));    //write remains
                    bw.flush();
                } else {
                    bw.append(contents.substring(i, i + BUFFER_SIZE));
                }
            }
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException ignored) {
                }
            }
            if(osw != null) {
                try {
                    osw.close();
                } catch (IOException ignored) {
                }
            }
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
