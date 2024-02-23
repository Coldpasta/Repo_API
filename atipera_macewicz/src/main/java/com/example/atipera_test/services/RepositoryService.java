package com.example.atipera_test.services;

import com.example.atipera_test.models.Branch;
import com.example.atipera_test.models.Repository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryService {

    private final RestClient client = RestClient.create();

    public List<Repository> listRepositories(String username) {
        try {
            List<Repository> result = client.get().uri(String.format("https://api.github.com/users/%s/repos", username)).retrieve().body(new ParameterizedTypeReference<>() {
            });
            result = result.stream().filter(it -> !it.isFork()).peek(it -> it.setBranches(listOfBranches(username, it.getRepoName()))).collect(Collectors.toList());
            return result;
        } catch (HttpClientErrorException.NotFound exception) {
            return null;
        }
    }

    public List<Branch> listOfBranches(String username, String repoName) {
        return client.get().uri(String.format("https://api.github.com/repos/%s/%s/branches", username, repoName)).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }
}
