package algorithms.other_algorithms.test_program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mati on 11.09.2017.
 */
public class ReadFromFile implements Callable<String> {

    private String fileName;
    private Integer stringLength;
    private String startString;
    private String endString;
    private Matcher matcher;
    private Pattern pattern;
    private boolean matchFound;

    public ReadFromFile(String fileName, Integer stringLength, String startString, String endString) {
        this.fileName = fileName;
        this.stringLength = stringLength;
        this.startString = startString;
        this.endString = endString;
    }

    @Override
    public String call() throws Exception {
        String everything = null;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            int startCode = (int) startString.charAt(0); // unicode
            int endCode = (int) endString.charAt(0);

            pattern = Pattern.compile(".dog"+endString, Pattern.DOTALL);


            while (line != null) {
                // use only stringLength words
                if (line.length() == stringLength && !((int)line.charAt(0) < startCode) &&(line.hashCode() >= startString.hashCode() && line.hashCode() <= endString.hashCode())) {
                    //if (line.length() == stringLength && ((int)line.charAt(0) >= startCode && (int) line.charAt(0) <= endCode)) {
                    matcher = pattern.matcher(line);
                    matchFound= matcher.matches();
                    if (matchFound) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    }
                }
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return everything;
    }
}
