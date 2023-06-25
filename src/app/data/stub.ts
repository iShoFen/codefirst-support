import {IssueStatus} from "../model/issues/IssueStatus";
import {Category} from "../model/issues/Category";
import {IssueSummary} from "../model/issues/IssueSummary";
import {Issue} from "../model/issues/Issue";
import {IssueModelInfo} from "../model/issues/IssueModelInfo";

export const ISSUE_LIST = [
  new IssueSummary(
    "1",
    "Problème de connexion",
    "John Doe",
    new Date(),
    IssueStatus.OPEN,
    new Category("bug")
  ),
  new IssueSummary(
    "2",
    "Ajouter une fonctionnalité de recherche",
    "Jane Smith",
    new Date(),
    IssueStatus.OPEN,
    new Category("feature")
  ),
  new IssueSummary(
    "3",
    "Erreur lors de la sauvegarde",
    "Robert Johnson",
    new Date(),
    IssueStatus.CLOSED,
    new Category("feature")
  ),
  new IssueSummary(
    "4",
    "Améliorer l'interface utilisateur",
    "Emily Davis",
    new Date(),
    IssueStatus.OPEN,
    new Category("bug")
  ),
  new IssueSummary(
    "5",
    "Affichage incorrect des données",
    "Michael Wilson",
    new Date(),
    IssueStatus.CLOSED,
    new Category("bug")
  )
]

export const ISSUE_SELECTED = new Issue("", "", "", new Date(), IssueStatus.CLOSED, new Category(""), new IssueModelInfo("", "", "", ""), [], [])
