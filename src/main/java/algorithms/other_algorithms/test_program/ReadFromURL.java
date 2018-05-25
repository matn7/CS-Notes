package algorithms.other_algorithms.test_program;

import java.io.*;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mati on 11.09.2017.
 */
public class ReadFromURL implements Callable<String> {

    private String stringUrl;
    private Integer stringLength;
    private String startString;
    private String endString;
    private Matcher matcher;
    private Pattern pattern;
    private boolean matchFound;

    public ReadFromURL(String stringUrl, Integer stringLength, String startString, String endString) {
        this.stringUrl = stringUrl;
        this.stringLength = stringLength;
        this.startString = startString;
        this.endString = endString;
    }

    @Override
    public String call() throws Exception {
        String everything = null;
        URL url = new URL(stringUrl);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            int startCode = (int) startString.charAt(0); // unicode
            int endCode = (int) endString.charAt(0);

            pattern = Pattern.compile("^[\\\\dog].*|^[^\\\\dog].*", Pattern.DOTALL);


            while (line != null) {
                // use only stringLength words
                if (line.length() == stringLength && !((int)line.charAt(0) < startCode) &&(line.hashCode() >= startString.hashCode() && line.hashCode() <= endString.hashCode())) {
                    //if (line.length() == stringLength && ((int)line.charAt(0) >= startCode && (int) line.charAt(0) <= endCode)) {
                    /*matcher = pattern.matcher(line);
                    matchFound= matcher.matches();
                    if (matchFound) {*/
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    //}
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
