package io.github.stewseo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import static io.github.stewseo.PrintUtil.cyan;
/**
 * Utility class for extracting the checkstyle.xml used by other plugins
 */
public abstract class CheckstyleUtil {

    public static String getCheckstyleConfig(String resourcePath) throws IOException {

        System.out.println(cyan("----------------------- resourcePath: " + resourcePath));

        InputStream in = new FileInputStream(resourcePath);  // name – the system-dependent file name.

//        InputStream in =  CheckstyleUtil.class.getResourceAsStream(resourcePath);

        StringBuilder sb = new StringBuilder();

        try (Reader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}