package dhbw.demo.text_search;

import java.util.List;

public class FullTextSearch {

    public static boolean textMatchesRegExSubString(String text, String searchedString) {
        return text.matches(WildcardEvaluator.createRegexForSubString(searchedString));
    }

    public static boolean textContainsLiteralSubstring(String text, String searchedString) {
        return text.contains(searchedString);
    }

    public static boolean textMatchesExactRegExString(String text, String searchedString) {
        return text.matches(WildcardEvaluator.createRegExForExactMatch(searchedString));
    }


    public static boolean textMatchesRegExSubString(String text, List<String> searchedStrings){
        return searchedStrings.stream().allMatch(searchedString -> textMatchesRegExSubString(text, searchedString));
    }

    public static boolean textContainsLiteralSubstring(String text, List<String> searchedStrings){
        return searchedStrings.stream().allMatch(searchedString -> textContainsLiteralSubstring(text, searchedString));
    }
}