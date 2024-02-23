package com.example.atipera_test.controllers;

import com.example.atipera_test.services.RepositoryService;
import com.example.atipera_test.models.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

        /*Acceptance criteria:
        As an api consumer, given username and header “Accept: application/json”, I would like to list all his github repositories, which are not forks.
        Information, which I require in the response, is:
        Repository Name
        Owner Login
        For each branch it’s name and last commit sha


        As an api consumer, given not existing github user, I would like to receive 404 response in such a format:
        {
        “status”: ${responseCode}
        “message”: ${whyHasItHappened}
        }*/


@RestController
public class RepositoryController {

    @Autowired
    private RepositoryService service;

    @RequestMapping(value = "/repositories/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    ResponseEntity<Object> getData(@PathVariable String username) {
        List<Repository> listOfRepo = service.listRepositories(username);
        if (listOfRepo == null) {
            return new ResponseEntity<>(String.format("{\"status\": %d, \"message\": \"user %s was not found\"}", HttpStatus.NOT_FOUND.value(), username), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(listOfRepo, HttpStatus.OK);

    }

}
