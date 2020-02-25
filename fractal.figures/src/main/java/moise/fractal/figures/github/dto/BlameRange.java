package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlameRange {
    private final Integer startingLine;
    private final Integer endingLine;
    private final Commit commit;

    @JsonCreator
    public BlameRange(
            @JsonProperty("startingLine") Integer startingLine,
            @JsonProperty("endingLine") Integer endingLine,
            @JsonProperty("commit") Commit commit) {
        this.startingLine = startingLine;
        this.endingLine = endingLine;
        this.commit = commit;
    }

    public Commit getCommit() {
        return commit;
    }

    public Integer getEndingLine() {
        return endingLine;
    }

    public Integer getStartingLine() {
        return startingLine;
    }
}
