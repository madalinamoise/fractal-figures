package moise.fractal.figures.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class RepositoryContentResponse {
    private final List<RepositoryContent> repositoryContentList;

    @JsonCreator
    public RepositoryContentResponse(List<RepositoryContent> repositoryContentList) {
        this.repositoryContentList = repositoryContentList;
    }

    public List<RepositoryContent> getRepositoryContentList() {
        return repositoryContentList;
    }
}
