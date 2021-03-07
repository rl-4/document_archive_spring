package dhbw.demo.text_search;

import java.util.List;

public class FullTextSearch {

    public static boolean textMatchesRegExSubString(String text, String searchedString) {
        String regExString = WildcardEvaluator.createRegexForSubString(searchedString);
        return text.matches(regExString);
    }

    public static boolean textContainsLiteralSubstring(String text, String searchedString) {
        return text.contains(searchedString);
    }

    public static boolean textMatchesExactRegExString(String text, String searchedString) {
        String regExString = WildcardEvaluator.createRegExForExactMatch(searchedString);
        return text.matches(regExString);
    }


    public static boolean textMatchesRegExSubString(String text, List<String> searchedStrings) {
        return searchedStrings.stream().allMatch(searchedString -> textMatchesRegExSubString(text, searchedString));
    }

    public static boolean textContainsLiteralSubstring(String text, List<String> searchedStrings) {
        return searchedStrings.stream().allMatch(searchedString -> textContainsLiteralSubstring(text, searchedString));
    }


    public static boolean textMatchesRegExSubString(String[] textCells, String searchedString) {
        for (String textCell : textCells) {
            if (textMatchesRegExSubString(textCell, searchedString)) {
                return true;
            }
        }
        return false;
    }

    public static boolean textMatchesRegExSubString(String[] textCells, List<String> searchedStrings) {
        return searchedStrings.stream().allMatch(searchedString -> textMatchesRegExSubString(textCells, searchedString));
    }

    public static boolean textContainsLiteralSubstring(String[] textCells, String searchedString) {
        for (String textCell : textCells) {
            if (textContainsLiteralSubstring(textCell, searchedString)) {
                return true;
            }
        }
        return false;
    }
}