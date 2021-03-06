package dhbw.demo;

import dhbw.demo.text_search.FullTextSearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFullTextSearch {
    //TODO tests for String[]

    @Test
    public void testTextMatchesRegExSubStringSingleChar() {
        boolean textMatchesSearchedString;

        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("Bells", "B?ll");
        Assertions.assertTrue(textMatchesSearchedString);

        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("year: 2021", "20??");
        Assertions.assertTrue(textMatchesSearchedString);

        //special chars allowed
        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("year: 2021", "year? 2021");
        Assertions.assertTrue(textMatchesSearchedString);


        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("Bowls", "B?ll");
        Assertions.assertFalse(textMatchesSearchedString);

        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("Bal", "B?ll");
        Assertions.assertFalse(textMatchesSearchedString);
    }

    @Test
    public void testTextMatchesRegExSubStringMultipleChars() {
        boolean textMatchesSearchedString;

        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("Bowls", "B*l");
        Assertions.assertTrue(textMatchesSearchedString);

        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("year: 2021", "year*21");
        Assertions.assertTrue(textMatchesSearchedString);

        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("year: 2021", "*");
        Assertions.assertTrue(textMatchesSearchedString);

        //at least one char
        textMatchesSearchedString = FullTextSearch.textMatchesRegExSubString("test", "te*st");
        Assertions.assertFalse(textMatchesSearchedString);
    }

    @Test
    public void testTextContainsLiteralSubstring(){
        boolean textContainsSubstring;

        textContainsSubstring = FullTextSearch.textContainsLiteralSubstring("Bowls", "Bowl");
        Assertions.assertTrue(textContainsSubstring);

        textContainsSubstring = FullTextSearch.textContainsLiteralSubstring("year:2021", ":2021");
        Assertions.assertTrue(textContainsSubstring);


        textContainsSubstring = FullTextSearch.textContainsLiteralSubstring("Bow", "Bowl");
        Assertions.assertFalse(textContainsSubstring);

        textContainsSubstring = FullTextSearch.textContainsLiteralSubstring("Bowl", "Bl");
        Assertions.assertFalse(textContainsSubstring);
    }

    @Test
    public void testTextMatchesExactRegExString(){
        boolean textMatchesExactRegExString;

        textMatchesExactRegExString = FullTextSearch.textMatchesExactRegExString("Bowls", "B?wls");
        Assertions.assertTrue(textMatchesExactRegExString);

        textMatchesExactRegExString = FullTextSearch.textMatchesExactRegExString("Bowls", "B?wl");
        Assertions.assertFalse(textMatchesExactRegExString);

        textMatchesExactRegExString = FullTextSearch.textMatchesExactRegExString("Bowls", "B*ls");
        Assertions.assertTrue(textMatchesExactRegExString);

        textMatchesExactRegExString = FullTextSearch.textMatchesExactRegExString("Bowls", "B*l");
        Assertions.assertFalse(textMatchesExactRegExString);
    }

    @Test
    public void testTextContainsLiteralSubstringList(){
        boolean textContainsSubstringList;

        List<String> searchedStrings = new ArrayList<>();
        searchedStrings.add("bell");
        searchedStrings.add("bowl");

        textContainsSubstringList = FullTextSearch.textContainsLiteralSubstring("bell and bowl", searchedStrings);
        Assertions.assertTrue(textContainsSubstringList);

        textContainsSubstringList = FullTextSearch.textContainsLiteralSubstring("Bell and bowl", searchedStrings);
        Assertions.assertFalse(textContainsSubstringList);

        searchedStrings.add("Ball");

        textContainsSubstringList = FullTextSearch.textContainsLiteralSubstring("bell and bowl", searchedStrings);
        Assertions.assertFalse(textContainsSubstringList);
    }

    @Test
    public void testTextMatchesRegExSubStringList(){
        boolean textMatchesSearchedStringList;

        List<String> searchedStrings = new ArrayList<>();
        searchedStrings.add("b*l");
        searchedStrings.add("bo?l");

        textMatchesSearchedStringList = FullTextSearch.textMatchesRegExSubString("bell and bowl", searchedStrings);
        Assertions.assertTrue(textMatchesSearchedStringList);

        searchedStrings.add("b??wl");

        textMatchesSearchedStringList = FullTextSearch.textMatchesRegExSubString("bell and bowl", searchedStrings);
        Assertions.assertFalse(textMatchesSearchedStringList);
    }
}
