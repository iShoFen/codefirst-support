# Codefirst Support

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