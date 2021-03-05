package dhbw.demo;

import dhbw.demo.text_search.WildcardEvaluator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

public class TestWildcardEvaluator {
    @TestFactory
    public Stream<DynamicTest> testWildcardEvaluationForSubstrings() {
        return Arrays.asList(
                new TestCase("B?ll", ".*B.ll.*"),
                new TestCase("B*ll", ".*B.+ll.*")
        ).stream().map(testCase -> DynamicTest.dynamicTest(testCase.wildcardString + " should be evaluated to " + testCase.expectedRegExString,
                () -> {
                    String actualRegExString = WildcardEvaluator.createRegexForSubString(testCase.wildcardString);
                    Assertions.assertEquals(testCase.expectedRegExString, actualRegExString);
                }
        ));
    }

    @TestFactory
    public Stream<DynamicTest> testWildcardEvaluationForExactMatch() {
        return Arrays.asList(
                new TestCase("B?ll", "B.ll"),
                new TestCase("B*ll", "B.+ll")
        ).stream().map(testCase -> DynamicTest.dynamicTest(testCase.wildcardString + " should be evaluated to " + testCase.expectedRegExString,
                () -> {
                    String actualRegExString = WildcardEvaluator.createRegExForExactMatch(testCase.wildcardString);
                    Assertions.assertEquals(testCase.expectedRegExString, actualRegExString);
                }
        ));
    }

    private static class TestCase {
        public String wildcardString;
        public String expectedRegExString;

        public TestCase(String wildcardString, String expectedRegExString) {
            this.wildcardString = wildcardString;
            this.expectedRegExString = expectedRegExString;
        }
    }
}
