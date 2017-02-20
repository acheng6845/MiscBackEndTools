import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by achen on 2/19/2017.
 */
public class PADImageExtractor {
    public static void main(String[] args) throws IOException, InterruptedException {

        String folder = "D:\\Users\\achen\\Documents\\Personal\\apk\\Puzzle-and-Dragons-Texture-Tool-master\\BC";
        File dir = new File(folder);
        File[] listOfFiles = dir.listFiles();

        for(File f : listOfFiles != null ? listOfFiles : new File[0]) {
            if (new File(folder+"\\Extracted\\"+f.getName().substring(0, f.getName().length() - 2)+".png").exists()) {
                if (f.isFile() && Objects.equals(f.getName().substring(f.getName().length() - 2), "bc")) {
                    String exec = "python \"" + folder + "\\PADTextureTool.py\" \"" + folder + '\\' + f.getName() + "\" --outdir \"" + folder + "\\Extracted\"";
                    System.out.println(exec);
                    Process p = Runtime.getRuntime().exec(exec);
                    int exitVal = p.waitFor();
                    System.out.println("Extracting " + f.getName() + " " + exitVal);
                }
            } else {
                System.out.println("Already there!");
            }
        }
    }
}
