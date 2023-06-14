import {Issue} from "../../model/issues/Issue";
import {PayloadAction} from "@reduxjs/toolkit";
import {SET_ISSUES} from "../constants";

export const setIssues = (issues: Issue[]): PayloadAction<Issue[]> => {
  return {
    type: SET_ISSUES,
    payload: issues
  }
}
