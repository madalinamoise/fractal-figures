package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlameRepository {
    private final BlameObject object;

    @JsonCreator
    public BlameRepository(@JsonProperty("object") BlameObject object) {
        this.object = object;
    }

    public BlameObject getObject() {
        return object;
    }
}
