import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
    public static void main(String[] args) {

        String input = "sep 17 2022 03 40";
        System.out.println(" " + input.length());
        String[] splitDate = input.split(" ");
        int c = 0;
        for (String s : splitDate) {
            System.out.println(s);
            if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
                break;
            }
            c++;
        }
        String lowerStr = splitDate[1].toLowerCase();
        String month = "";
        if (splitDate[c].length() > 3) {
            month = (String) splitDate[c].subSequence(0, 3);
            splitDate[c] = splitDate[c].replaceAll(splitDate[c], month);
        }
        String s1 = "";
        for (String s : splitDate) {
            s1 = s1 + s + " ";
        }
        System.out.println(s1.length() + " " + input.length());
        input = s1;
        System.out.println(input);
    }

}
