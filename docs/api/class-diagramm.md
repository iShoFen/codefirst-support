# Diagramme de classe des entités

```plantuml
@startuml
class Category {
  name: string
}

class Issue {
  _id: ObjectId
  title: string
  author: string
  created_at: date
  status: string
}

class IssueModel {
  _id: ObjectId
}

class Comment {
  created_at: date
  author: string
  content: string
}

class IssueField {
  value: string
}

class IssueModelField {
  title: string
  description: string
  required: boolean
}

class IssueModelInfo {
  name: string
  short_description: string
  description: string
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
  _id: ObjectId
  created_at: date
  published_at: date
  end_at: date
  title: string
  description: string
}

class Question {
  description: string
  choices: [string]
}

enum QuestionType {
  text
  singlechoice
  multiplechoice
}

class Feedback {
  _id: ObjectId
  survey_id: ObjectId
  created_at: date
  author: string
  answers: [string]
}

class QuestionInfo {
  title: string
}

Survey -up-> "questions *" Question
Feedback --> "question 1" QuestionInfo
QuestionInfo -right-> "type 1" QuestionType

Question -up-|> QuestionInfo
@enduml
```
