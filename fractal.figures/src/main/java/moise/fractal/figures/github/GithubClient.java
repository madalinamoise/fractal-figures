package moise.fractal.figures.github;

import moise.fractal.figures.domain.FileContributions;
import moise.fractal.figures.github.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class GithubClient {

    private static final String GITHUB_API_URL = "https://api.github.com";
    private static final String GITHUB_GRAPHQL_URL = "https://api.github.com/graphql";

    private final RestTemplate template;
    private final HttpHeaders headers;

    @Value("classpath:blame-query.graphql")
    private Resource resource;

    public GithubClient(RestTemplate template, HttpHeaders headers) {
        this.template = template;
        this.headers = headers;
    }

    public void getRepositories() {
        ResponseEntity<RepositoriesResponse> repositories = template.exchange(GITHUB_API_URL + "/user/repos",
                HttpMethod.GET, new HttpEntity<>("", headers), RepositoriesResponse.class);
        repositories.getBody().getRepositories().stream().forEach(this::getRepositoryContent);
    }

    public void getRepositoryContent(Repository repository) {
        String repoUrl = GITHUB_API_URL + "/repos/" + repository.getFullName() + "/contents";
        ResponseEntity<RepositoryContentResponse> repositoryResponse = template.exchange(repoUrl, HttpMethod.GET, new HttpEntity<>("", headers), RepositoryContentResponse.class);
        repositoryResponse.getBody().getRepositoryContentList().forEach(content -> parseRepositoryContent(repository, content));
    }

    private void parseRepositoryContent(Repository repository, RepositoryContent content) {
        if ("dir".equals(content.getType())) {
            String repoUrl = GITHUB_API_URL + "/repos/" + repository.getFullName() + "/contents/" + content.getPath();
            ResponseEntity<RepositoryContentResponse> contentResponse = template.exchange(repoUrl, HttpMethod.GET, new HttpEntity<>("", headers), RepositoryContentResponse.class);
            contentResponse.getBody().getRepositoryContentList().forEach(cnt -> parseRepositoryContent(repository, cnt));
        } else if ("file".equals(content.getType())) {
            String[] repositoryName = repository.getFullName().split("/");
            FileContributions contributions = getFileContributors(repositoryName[0], repositoryName[1], content.getPath());
            System.out.println(content.getPath() + ": " + contributions);
        }
    }

    private FileContributions getFileContributors(String owner, String repository, String path) {
        String blameQuery = convertBlameQueryToString()
                .replace("#owner#", owner)
                .replace("#repository#", repository)
                .replace("#file_path#", path);
        ResponseEntity<BlameResponse> response = template.exchange(GITHUB_GRAPHQL_URL, HttpMethod.POST, new HttpEntity<>(blameQuery, headers), BlameResponse.class);
        List<BlameRange> ranges = response.getBody().getData().getRepository().getObject().getBlame().getRanges();
        Map<String, Double> contributorLines = new HashMap<>();
        int numberOfLines = 0;
        for(BlameRange range : ranges) {
            String contributor = range.getCommit().getAuthor().getName();
            Double lines = contributorLines.getOrDefault(contributor, 0D) + range.getEndingLine() - range.getStartingLine() + 1;
            contributorLines.put(contributor, lines);
            numberOfLines = Math.max(numberOfLines, range.getEndingLine());
        }

        FileContributions contributions = new FileContributions();
        contributions.setTotalLines(numberOfLines);
        contributions.setContributorLines(contributorLines);

        return contributions;
    }

    public String convertBlameQueryToString() {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new RuntimeException("Oh well . . .");
        }
    }

}
