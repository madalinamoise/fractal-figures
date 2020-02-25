package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Commit {
    private final CommitAuthor author;

    @JsonCreator
    public Commit(@JsonProperty("author") CommitAuthor author) {
        this.author = author;
    }

    public CommitAuthor getAuthor() {
        return author;
    }
}
