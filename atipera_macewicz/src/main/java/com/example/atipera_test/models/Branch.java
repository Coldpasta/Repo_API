package com.example.atipera_test.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data
public class Branch {

    private String sha;
    @JsonProperty("name")
    private String name;

    @JsonProperty("commit")
    private void unpackCommit(Map<String, Object> commit) {
       this.sha = (String) commit.get("sha");

    }
}
