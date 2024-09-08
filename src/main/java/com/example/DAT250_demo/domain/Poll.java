package com.example.DAT250_demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public class Poll {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private List<VoteOption> voteOptions;

    @JsonCreator
    public Poll(@JsonProperty("question") String question,
                @JsonProperty("publishedAt") Instant publishedAt,
                @JsonProperty("validUntil") Instant validUntil,
                @JsonProperty("voteOptions") List<VoteOption> voteOptions) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.voteOptions = voteOptions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }
}

