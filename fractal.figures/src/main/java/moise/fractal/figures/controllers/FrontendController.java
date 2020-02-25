package moise.fractal.figures.controllers;

import moise.fractal.figures.github.GithubClient;
import moise.fractal.figures.github.dto.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init-api")
public class FrontendController {
    private final GithubClient client;

    public FrontendController(GithubClient client) {
        this.client = client;
    }

    @GetMapping
    public String helloWorld(){
        client.getRepositoryContent(new Repository("FlorinMsg/TeamFixers"));
//        client.getFileContributors("FlorinMsg", "TeamFixers", "src/main/java/fixers/jBugger/BusinessLogic/BugEJB.java");
        return "Hello World!!";
    }
}
