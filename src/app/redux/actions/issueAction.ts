import {Issue} from "../../model/issues/Issue";
import {PayloadAction} from "@reduxjs/toolkit";
import {SET_ISSUE_LOADING, SET_ISSUES, SET_SELECTED_ISSUE} from "../constants";
import {IssueSummary} from "../../model/issues/IssueSummary";

export const setIssues = (issues: IssueSummary[]): PayloadAction<IssueSummary[]> => {
  return {
    type: SET_ISSUES,
    payload: issues
  }
}

export const setSelectedIssue = (issue: Issue): PayloadAction<Issue | undefined> => {
  return {
    type: SET_SELECTED_ISSUE,
    payload: issue
  }
}

export const setIssueLoading = (loading: boolean): PayloadAction<boolean> => {
  return {
    type: SET_ISSUE_LOADING,
    payload: loading
  }
}
