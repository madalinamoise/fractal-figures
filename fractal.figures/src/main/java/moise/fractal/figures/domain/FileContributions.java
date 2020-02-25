package moise.fractal.figures.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class FileContributions {

    private Integer totalLines;

    private Map<String, Double> contributorLines;

    private Map<String, String> contributorPercentage;

    public Integer getTotalLines() {
        return totalLines;
    }

    public void setTotalLines(Integer totalLines) {
        this.totalLines = totalLines;
    }

    public void setContributorLines(Map<String, Double> contributorLines) {
        this.contributorLines = contributorLines;
    }

    public Map<String, String> getContributorPercentage() {
        if (contributorPercentage == null) {
            contributorPercentage = new HashMap<>();
            for(String contributor : contributorLines.keySet()) {
                contributorPercentage.put(contributor, String.format("%.2f", contributorLines.get(contributor) / totalLines * 100) + "%");
            }
        }

        return contributorPercentage;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");
        for(Map.Entry<String, String> contributor : getContributorPercentage().entrySet()) {
            joiner.add(contributor.getKey() + "=" + contributor.getValue());
        }

        return joiner.toString();
    }
}
