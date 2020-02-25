package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlameObject {
    private final Blame blame;

    @JsonCreator
    public BlameObject(@JsonProperty("blame") Blame blame) {
        this.blame = blame;
    }

    public Blame getBlame() {
        return blame;
    }
}
