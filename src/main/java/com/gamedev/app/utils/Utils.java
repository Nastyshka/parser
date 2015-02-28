package com.gamedev.app.utils;

import java.io.*;

/**
 * Created by MainUser on 28/02/2015.
 */
public class Utils {

    public static String readFile(String file){
        String result = "";
        try {
            result = read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeFile(String fileName, String content){
        try {
            write(fileName, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String read(String file) throws IOException {
        String everything = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
             everything = sb.toString();
        } finally {
            br.close();
        }
        return everything;
    }

    private static void write(String fileName, String content) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(content);
        writer.close();
    }
}
