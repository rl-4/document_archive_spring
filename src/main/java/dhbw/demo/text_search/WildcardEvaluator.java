package dhbw.demo.text_search;

public class WildcardEvaluator {
    //b?ll -> exactly 1 char
    //b*ll -> more then one chars

    public static String createRegExForExactMatch(String searchPattern) {
        return searchPattern.replace("?", ".").replace("*", ".+");
    }

    public static String createRegexForSubString(String searchPattern) {
        return ".*" + createRegExForExactMatch(searchPattern) + ".*";
    }
}
