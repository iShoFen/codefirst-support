# Explication des champs du modèle

Cette explication rassemble toutes les collections de notre modèle pour l'application **Codefirst-Support**.

## Issues

Cette collection représente les tickets de notre application.

- `_id` : ObjectId
    
    Identifiant du ticket

- `title` : string

    Titre du ticket

- `fields` : array

    Liste des champs à remplir dans le ticket
    
    - `title` : string
    
        Titre du champ
    
    - `description` : string
        
        Description du champ
    - `required` : boolean
        
        Indique si le champ est requis

    - `value` : string
    
        Réponse

- `author` : string

    Auteur du ticket

- `created_at` : date

    Date de création

- `status` : string
    
    Statut du ticket

- `category` : object

    Catégorie du ticket

    - `name` : string

        Nom de la catégorie du ticket

- `comments` : array

    Liste des commentaires du ticket

    - `created_at` : date

        Date de création du commentaire

    - `author` : string

        Auteur du commentaire

    - `content` : string

        Contenu du commentaire

- `model` : object

    Modèle du ticket

    - `name` : string

        Nom du modèle

    - `short_description` : string

        Description courte du modèle

    - `description` : string

        Description longue du modèle


## Issue Models

- `_id`
- `name`
- `short_description`
- `description`
- `category`
    - `name`
- `fields`
    - `title`
    - `description`
    - `required`


## Surveys

- `_id`
- `created_at`
- `published_at`
- `end_at`
- `title`
- `description`
- `questions`
    - `title`
    - `description`
    - `type`
    - `choices`

## Feedbacks

- `_id`
- `survey_id`
- `created_at`
- `author`
- `question`
    - `title`
    - `type`
- `answer`