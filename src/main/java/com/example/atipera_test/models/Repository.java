package com.example.atipera_test.models;

import com.example.atipera_test.models.Branch;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Repository {

    @JsonProperty("name")
    private String repoName;

    private String ownerLogin;


    @JsonProperty("owner")
    private void unpackOwner(Map<String, Object> owner) {
        this.ownerLogin = (String) owner.get("login");
    }

    @JsonProperty("fork")
    private boolean isFork;

    private List<Branch> branches;


}
