import {Issue} from "../../model/issues/Issue";
import {PayloadAction} from "@reduxjs/toolkit";
import {SET_ISSUES, SET_SELECTED_ISSUE} from "../constants";

export const setIssues = (issues: Issue[]): PayloadAction<Issue[]> => {
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
