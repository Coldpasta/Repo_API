# Repositories API

### Overview
The Repository API is a simple RESTful API built in Java that retrieves a list of GitHub's public repositories for a given username.

### Usage
The API utilizes a single GET endpoint:

``` curl
GET /repositories/{username}
```
Replace `{username}` with the desired user's GitHub username.

#### Example Response (Successful):
``` JSON
[
  {
    "ownerLogin": "coldpasta",
    "name": "lukasz.github.io",
    "fork": false,
    "branches": [
      {
        "sha": "1a241b509ddf9cd11814b862db4d1f9a5e2a732f",
        "name": "main"
      },
      {
        "sha": "214mxzisaddf9cd11814b862db4d1f9a5e2a732d",
        "name": "develop"
      }
    ]
  }
]
```
Note: This example is redacted and may not represent all fields returned by the API.

### Error Handling:
* __404 Not Found__: The provided username does not exist or has no public repositories. The response body will contain a descriptive error message.

### Response Format
The API returns a JSON array containing repository objects. Each object includes details like:

* name: Name of the repository.
* ownerLogin: Username of the repository owner.
* fork: Boolean flag indicating if the repository is a fork.
* branches: Array of branch objects with details like:
    * sha: SHA hash of the branch's latest commit.
    * name: Name of the branch.

Note: Specific response fields may be subject to change.