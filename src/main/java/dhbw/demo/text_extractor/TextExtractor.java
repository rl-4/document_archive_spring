package dhbw.demo.text_extractor;

public abstract class TextExtractor {
    public String errorMessage = "Text could not be extracted";

    public abstract String extractText(String path);
}
