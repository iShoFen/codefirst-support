# Codefirst Support

Codefirst Support is an application for reporting issue and answering survey around [Codefirst](https://codefirst.iut.uca.fr/) eco-system.

This repository contains two projects:
- A [React Native](https://reactnative.dev/) application for the frontend: show more [here](docs/app/)
- A [Quarkus](https://quarkus.io/) API for the backend: show more [here](docs/api/)


## Authors
- Florent Marques
    - [![Florent Marques](https://img.shields.io/badge/-flomSStaar-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/flomSStaar)
    - [![Florent Marques](https://img.shields.io/badge/-Florent%20Marques-0077B5?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/florent-marques)
- Samuel Sirven
    - [![Samuel Sirven](https://img.shields.io/badge/-iShofen-181717?style=flat-square&logo=github&logoColor=white)](https://github.com/iShofen)
    - [![Samuel Sirven](https://img.shields.io/badge/-Samuel%20Sirven-0077B5?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/samuel-sirven-b49b53211/)

## Documentation

### Modèle conceptuel de données (MCD)

Vous pouvez retrouver le modèle conceptuel de données complet [ici](docs/api/mcd.md)

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

model_info ||--|{ category : contains

issue_model }o--{ model_field : contains
issue_model ||--|{ category : contains


survey }o--{ question : contains
survey }o--|| feedback : contains

feedback ||--|{ question_info : contains

@enduml
```

### Diagramme de classes des entités

Vous pouvez retrouver le diagramme de classes des entités complet [ici](docs/api/class-diagramm.md)

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

class IssueModelInfo {
}

Issue -up-> "category 1" Category
Issue --> "fields *" IssueField
Issue --> "comments *" Comment
Issue -down-> "model 1" IssueModelInfo

IssueModel --> "category 1" Category
IssueModel -left-> "fields *" IssueModelField

IssueModel -up-|> IssueModelInfo
IssueField -up-|> IssueModelField


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

Survey -up-> "comments *" Question
Feedback --> "question 1" QuestionInfo
QuestionInfo -right-> "type 1" QuestionType

Question -up-|> QuestionInfo
@enduml
```