package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RepositoryContent {
    private final String name;
    private final String path;
    private final String type;

    @JsonCreator
    public RepositoryContent(
            @JsonProperty("name") String name,
            @JsonProperty("path") String path,
            @JsonProperty("type") String type) {
        this.name = name;
        this.path = path;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "type = " + type + ", path = " + path;
    }
}
