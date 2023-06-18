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
  name: string
  short_description: string
  description: string
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

Issue --> "fields *" IssueField
Issue --> "comments *" Comment
Issue --> "model 1" IssueModel

IssueModel --> "category 1" Category
IssueModel --> "fields *" IssueModelField

IssueField --|> IssueModelField

@enduml
```
