package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository {
    private final String fullName;

    @JsonCreator
    public Repository(@JsonProperty("full_name") String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
