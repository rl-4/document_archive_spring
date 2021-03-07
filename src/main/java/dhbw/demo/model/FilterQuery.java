package dhbw.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FilterQuery {

    @JsonProperty("regExMatch")
    public boolean regExMatch;

    @JsonProperty("searchQuery")
    public String searchQuery;

    @JsonProperty("keyValuePairs")
    public Map<String, String> keyValuePairs = new HashMap<>();

    public FilterQuery() {

    }

    public FilterQuery(boolean regExMatch, String searchQuery, Map<String, String> keyValuePairs) {
        this.regExMatch = regExMatch;
        this.searchQuery = searchQuery;
        this.keyValuePairs = keyValuePairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterQuery)) return false;
        FilterQuery that = (FilterQuery) o;
        return regExMatch == that.regExMatch && Objects.equals(searchQuery, that.searchQuery) && Objects.equals(keyValuePairs, that.keyValuePairs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regExMatch, searchQuery, keyValuePairs);
    }
}
