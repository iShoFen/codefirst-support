# Availables endpoints

This document shows all available API endpoints.

## Surveys

- **GET** `/surveys` -> List all surveys
- **GET** `/surveys/{id}` -> Get the survey corresponding to the id
- **POST** `/surveys` -> Add a survey
- **PUT** `/surveys/{id}` -> Update the survey
- **DELETE** `/surveys/{id}` -> Delete the survey

### Feedbacks

- **GET** `/surveys/{id}/feedbacks` -> List all feedbacks of the survey
- **POST** `/surveys/{id}/feedbacks` -> Add a feedback for the survey
- **DELETE** `/surveys/{survey_id}/feedbacks/{feedback_id}` -> Delete a feedback

## Issues

- **GET** `/issues` -> List all issues
- **GET** `/issues/{id}` -> Get the issue corresponding to the id
- **POST** `/issues` -> Add a issue
- **PUT** `/issues/{id}` -> Update the issue
- **DELETE** `/issues/{id}` -> Delete the issue

### Status

- **POST** `/issues/{id}/status` -> Update the status of an issue

    List of status:
    - open
    - closed

    ```json
    // Usage of status
    {
        "status": "open"
    }
    ```

### Comments

- **POST** `/issues/{id}/comments` -> Post a new comment for the issue
- **DELETE** `/issues/{issue_id}/comments/{comment_id}` -> Post a new comment for the issue

## Issue models

- **GET** `/issues/models` -> List all issue models
- **GET** `/issues/models/{id}` -> Get the issue model corresponding to the id
- **POST** `/issues/models` -> Add a issue model
- **PUT** `/issues/models/{id}` -> Update a issue model
- **DELETE** `/issues/models/{id}` -> Delete a issue model