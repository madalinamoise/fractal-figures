package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlameData {
    private final BlameRepository repository;

    @JsonCreator
    public BlameData(@JsonProperty("repository") BlameRepository repository) {
        this.repository = repository;
    }

    public BlameRepository getRepository() {
        return repository;
    }
}
