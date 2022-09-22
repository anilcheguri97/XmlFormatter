package com.logit.one;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConvertUrltoData {
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://www.osee.in/");
            URLConnection urlcon = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            String line;
            File file = new File("src/main/resources/newFile.txt");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);

                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            bufferedReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println(url.getProtocol());
//        System.out.println(url.getFile());

    }
}
