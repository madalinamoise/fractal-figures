package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class RepositoriesResponse {
    private final List<Repository> repositories;

    @JsonCreator
    public RepositoriesResponse(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }
}
