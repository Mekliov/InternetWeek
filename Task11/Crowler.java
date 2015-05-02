package Task1;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crowler {
    static String urlAdress = "http://ebusiness.free.bg/";

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        String out = new Scanner(new URL(urlAdress).openStream(), "UTF-8").useDelimiter("\\A").next();

        Crowler.getAllLinks(out);

    }

    private static List<String> getAllLinks(String content) throws IOException {
        ArrayList<String> resultList = new ArrayList<>();
        String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int a = 0;
        while (matcher.find()) {

            resultList.add(a, matcher.group(1));
            a++;

        }

        Crowler.needleContent(resultList);
        return resultList;
    }

    public static void needleContent(ArrayList<String> list) throws IOException {

        String needle = "Револвираща";
        for (int i = 0; i < list.size(); i++) {
            try {
                String out = new Scanner(new URL(urlAdress + list.get(i)).openStream(), "UTF-8").useDelimiter("\\A")
                        .next();

                if (out.contains(needle) == true) {
                    System.out.println(urlAdress + list.get(i));
                    System.exit(1);
                }
            } catch (IOException e) {
                continue;
            }
        }

    }
}
