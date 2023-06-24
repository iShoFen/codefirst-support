import {Issue} from "../../model/issues/Issue";
import {SET_ISSUES, SET_SELECTED_ISSUE, SET_ISSUE_LOADING} from "../constants";
import {IssueSummary} from "../../model/issues/IssueSummary";

export type IssueReducerState = {
  issues: IssueSummary[]
  selectedIssue?: Issue
  loading: boolean
}

const initialState: IssueReducerState = {
  issues: [],
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
    case SET_ISSUE_LOADING:
      return {
        ...state,
        loading: action.payload
      }
    default:
      return state
  }
}

export default issueReducer
