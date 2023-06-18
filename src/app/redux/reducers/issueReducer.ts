import {Issue} from "../../model/issues/Issue";
import {IssueStatus} from "../../model/issues/IssueStatus";
import {Category} from "../../model/issues/Category";
import {SET_ISSUES, SET_SELECTED_ISSUE} from "../constants";
import {IssueSummary} from "../../model/issues/IssueSummary";

type IssueReducerState = {
  issues: IssueSummary[]
  selectedIssue?: Issue
  loading: boolean
}

const initialState: IssueReducerState = {
  issues: [
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
  ],
  loading: false,
  selectedIssue: undefined
};

//@ts-ignore
const issueReducer = (state: IssueReducerState = initialState, action): IssueReducerState => {
  switch (action.type) {
    case SET_ISSUES:
      return {
        ...state,
        issues: action.payload
      }
    case SET_SELECTED_ISSUE:
      return {
        ...state,
        selectedIssue: action.payload
      }
    default:
      return state
  }
}

export default issueReducer
