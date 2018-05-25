package algorithms.other_algorithms.test_program.v1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromURL implements Callable<List<String>> {

    private String stringUrl;
    private Integer stringLength;
    private String startString;
    private String endString;
    private Matcher matcher;
    private Pattern pattern;
    private boolean matchFound;
    List<String> lista;


    public ReadFromURL(String stringUrl, Integer stringLength, String startString, String endString) {
        this.stringUrl = stringUrl;
        this.stringLength = stringLength;
        this.startString = startString;
        this.endString = endString;
    }

    @Override
    public List<String> call() throws Exception {
        lista = new ArrayList<>();
        URL url = new URL(stringUrl);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line = br.readLine();

            int startCode = (int) startString.charAt(0); // unicode
            int endCode = (int) endString.charAt(0);

            while (line != null) {
                // filter based on word length
                if (line.length() == stringLength) {
                    // filter to start from start word
                    if (!((int) line.charAt(0) < startCode)) {
                        // hashCode from startString to endString
                        if ((line.hashCode() >= startString.hashCode() && line.hashCode() <= endString.hashCode())) {
                            lista.add(line);
                        }
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
