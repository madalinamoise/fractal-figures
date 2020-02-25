package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Blame {
    private final List<BlameRange> ranges;

    @JsonCreator
    public Blame(@JsonProperty("ranges") List<BlameRange> ranges) {
        this.ranges = ranges;
    }

    public List<BlameRange> getRanges() {
        return ranges;
    }
}
