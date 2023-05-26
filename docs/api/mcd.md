## Ticket:
- Titre
- Contenu
- Auteur (mail)
- Date de création
- Statut
- Catégorie (incorporation)
    - Nom
- Commentaires (incorporation, one to few)
    - Texte
    - Date
    - Auteur (mail)

## Modèle
Exemple de modèle [ici](https://github.com/anuraghazra/github-readme-stats/issues/new/choose)
- Nom du modèle
- Description du modèle
- Catégorie
- Champs (incorporation, one to few)
    - Titre
    - Description

## Questionnaire
- Titre
- Description
- Champs (incorporation, one to few)
    - Titre
    - Description
    - Choix: [string]
    Si `Choix` est présent -> Sélection avec Radio/Checkbox
    Sinon type -> `Text`
    - Multiple (boolean)
    Permet de déterminer le type de choix Radio/Checkbox

## Retour
- Id du questionnaire (one to zillions, référence)
- Date de création
- Auteur (mail)
- Titre de la question
- Reponse (valeur ou tableau si choix multiple)