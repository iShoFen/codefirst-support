<p align="center">
    <img alt="codefirst support" src="../images/logo_codefirst.svg" width="200" />
</p>
<h1 align="center">Codefirst Support</h1>
<h3 align="center">Improve students courses</h3>

## Introduction

This application allows users to report issues and answer to surveys available on the app. 

Administrators can create issue models to define a template for the issues. Then, users can select a model and fill the field to post their issue. The admin can post comment to reply to the user to fix the issue.
Administrators can create surveys with some questions then users reply with a feedback.

## 📦 Getting started

Run the api in development mode:

```bash
$ mvwn quarkus:dev
```

## 🛠 Testing

Use the following command to run unit tests:

```bash
$ mvnw quarkus:test
```

## 📝 Documentation

To show the full diagram, click [here](mcd.md).

### Modèle conceptuel de données (MCD)

You can find the entire mcd diagram [here](docs/api/mcd.md).

```plantuml
@startuml

hide circle
hide empty members

entity "issue" as issue {
}

entity "model_info" as model_info {
}

entity "category" as category {
}

entity "model_field" as model_field {
}

entity "issue_field" as issue_field {
}

entity "comment" as comment {
}

entity "issue_model" as issue_model {
}

entity "survey" as survey {
}

entity "question" as question {
}

entity "question_info" as question_info {
}

entity "feedback" as feedback {
}

issue ||-o{ model_info : contains
issue }o--|| comment : contains
issue }o--|{ issue_field : contains 

issue ||--|{ category : contains

issue_model }o--{ model_field : contains
issue_model ||--|{ category : contains


survey }o--{ question : contains
survey }o--|| feedback : contains

feedback ||--|{ question_info : contains

@enduml
```

### Entity class diagram

#### Issues

You can find the entire diagram [here](docs/api/class-diagram-issue.md).

```plantuml
@startuml

class Category {
}

class Issue {
}

class IssueModel {
}

class Comment {
}

class IssueField {
}

class IssueModelField {
}

enum IssueStatus {
  UNDEFINED
  OPENED
  CLOSED
}

Issue --> "fields *" IssueField
Issue --> "comments *" Comment
Issue --> "model 1" IssueModel
Issue --> "status 1" IssueStatus

IssueModel --> "category 1" Category
IssueModel --> "fields *" IssueModelField

IssueField --|> IssueModelField

@enduml
```

#### Surveys

You can find the entire diagram [here](docs/api/class-diagram-survey.md).

```plantuml
@startuml

class Survey {
}

class Question {
}

enum QuestionType {
  text
  singlechoice
  multiplechoice
}

class Feedback {
}

class QuestionInfo {
}

Survey --> "questions *" Question
Feedback --> "question 1" QuestionInfo
QuestionInfo --> "type 1" QuestionType

Question -up-|> QuestionInfo

@enduml
```

### Database indexes

```json
db.feedbacks.createIndex({survey_id: 1})
```

```json
db.issue_models.createIndex({name: 1})
```

```json
db.issues.createIndex({status: 1, created_at: 1})
```
