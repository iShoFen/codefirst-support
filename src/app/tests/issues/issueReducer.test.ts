import {describe, expect, it} from "@jest/globals";
import issueReducer, {IssueReducerState} from "../../redux/reducers/issueReducer";
import {IssueSummary} from "../../model/issues/IssueSummary";
import {setIssueLoading, setIssues, setSelectedIssue} from "../../redux/actions/issueAction";
import {Issue} from "../../model/issues/Issue";
import {ISSUE_LIST, ISSUE_SELECTED} from "../../data/stub";


describe('test issue reducer', () => {

  let initialState: IssueReducerState = {
    issues: [],
    loading: false,
    selectedIssue: undefined
  }

  it('should return initial state', () => {
    expect(issueReducer(undefined, {})).toEqual(initialState);
  });

  it('should handle SET_ISSUES', () => {
    const issues: IssueSummary[] = ISSUE_LIST

    expect(
      issueReducer(initialState, setIssues(issues))
    ).toEqual({
      issues: issues,
      loading: false,
      selectedIssue: undefined
    })
  })

  it('should handle SET_SELECTED_ISSUE', () => {
    const issue: Issue = ISSUE_SELECTED

    expect(
      issueReducer(initialState, setSelectedIssue(issue))
    ).toEqual({
      issues: [],
      loading: false,
      selectedIssue: issue
    })

    expect(
      issueReducer({
        ...initialState,
        selectedIssue: issue
      }, setSelectedIssue(undefined))
    ).toEqual({
      issues: [],
      loading: false,
      selectedIssue: undefined
    })
  })

  it('should handle SET_ISSUE_LOADING', () => {
    expect(
      issueReducer(initialState, setIssueLoading(true))
    ).toEqual({
      issues: [],
      loading: true,
      selectedIssue: undefined
    })
  })

  expect(
    issueReducer({
      ...initialState,
      loading: true
    }, setIssueLoading(false))
  ).toEqual({
    issues: [],
    loading: false,
    selectedIssue: undefined
  })
})
