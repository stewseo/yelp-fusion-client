package io.github.stewseo.yelp.fusion.lowlevel.restclient;

@SuppressWarnings("UnusedReturnValue")
public class PrintUtils {

    public static void title(Object title) {
        println("");
        println("-".repeat(("" + title).length()));
    }
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String RED = "\u001B[31m";

    public static  <T> String  green(T title) {
        return GREEN + title + RESET;
    }
    public static  <T> String  red(T title) {
        return RED + title + RESET;
    }

    public static  <T> String debug(T title) {
        return (CYAN + title + RESET);
    }
    public static  <T> String tracer(T title) {
        return (RED + title + RESET);
    }

    public static  <T> String cyan(T title) {
        return CYAN + title + RESET;
    }
    public static <T> T print(T t) {
        System.out.print(t);
        return t;
    }
    public static <T> T println(T t) {
        System.out.println(t);
        return t;
    }

    public static <T> T println(String description, T t) {
        System.out.print("description: "+ description + " ");
        println(t);
        return t;
    }
}
