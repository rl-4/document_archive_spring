package dhbw.demo.text_search;

import dhbw.demo.model.TextExtractionResult;
import dhbw.demo.model.TextExtractionResultWrapper;

import java.util.ArrayList;
import java.util.List;

public class FilterDocumentsByFullTextSearch {
    public static int[] filterDocumentsByFullTextSearch(boolean regExMatch, TextExtractionResultWrapper textExtractionResultWrapper, String searchQuery) {
        TextExtractionResult[] textExtractionResults = textExtractionResultWrapper.textExtractionResults;

        List<Integer> filtered_document_ids = new ArrayList<>();
        for (TextExtractionResult textExtractionResult : textExtractionResults) {
            String extractedText = textExtractionResult.extractedText;
            String[] extractedCells = textExtractionResult.extractedCells;

            boolean textMatchesSearch;
            if (extractedText != null) {
                if (regExMatch) {
                    textMatchesSearch = FullTextSearch.textMatchesRegExSubString(extractedText, searchQuery);
                } else {
                    textMatchesSearch = FullTextSearch.textContainsLiteralSubstring(extractedText, searchQuery);
                }
            } else if (extractedCells != null) {
                if (regExMatch) {
                    textMatchesSearch = FullTextSearch.textMatchesRegExSubString(extractedCells, searchQuery);
                } else {
                    textMatchesSearch = FullTextSearch.textContainsLiteralSubstring(extractedCells, searchQuery);
                }
            } else {
                throw new RuntimeException("No extracted text");
            }
            if (textMatchesSearch) {
                filtered_document_ids.add(textExtractionResult.document_id);
            }
        }

        return filtered_document_ids.stream().mapToInt(i -> i).toArray();
    }
}
