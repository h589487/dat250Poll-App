package com.example.DAT250_demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Vote {
    private Instant publishedAt;
    private VoteOption voteOption;

    public Vote() {}

    @JsonCreator
    public Vote(@JsonProperty("publishedAt") Instant publishedAt,
                @JsonProperty("voteOption") VoteOption voteOption) {
        this.publishedAt = publishedAt;
        this.voteOption = voteOption;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(VoteOption voteOption) {
        this.voteOption = voteOption;
    }
}