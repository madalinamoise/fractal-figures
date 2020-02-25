package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlameResponse {
    private final BlameData data;

    @JsonCreator
    public BlameResponse(@JsonProperty("data") BlameData data) {
        this.data = data;
    }

    public BlameData getData() {
        return data;
    }
}
