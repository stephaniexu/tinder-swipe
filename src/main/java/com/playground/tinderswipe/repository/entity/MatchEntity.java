package com.playground.tinderswipe.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "match")
public class MatchEntity {
    @Id
    private String accountId;

    private List<String> matches;

    public MatchEntity() {}

    public MatchEntity(String accountId) {
        this.accountId = accountId;
        matches = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<String> getMatches() {
        return matches;
    }

    public void setMatches(List<String> matches) {
        this.matches = matches;
    }

    public void addMatch(String matchId) {
        if (matches == null) {
            matches = new ArrayList<>();
        }
        matches.add(matchId);
    }

    @Override
    public String toString() {
        return "MatchEntity{" +
                "accountId='" + accountId + '\'' +
                ", matches=" + matches +
                '}';
    }
}
