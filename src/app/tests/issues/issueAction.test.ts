import {describe, expect, it} from "@jest/globals";
import {SET_ISSUE_LOADING, SET_ISSUES, SET_SELECTED_ISSUE} from "../../redux/constants";
import {IssueSummary} from "../../model/issues/IssueSummary";
import {setIssueLoading, setIssues, setSelectedIssue} from "../../redux/actions/issueAction";
import {Issue} from "../../model/issues/Issue";
import {ISSUE_LIST, ISSUE_SELECTED} from "../../data/stub";

describe('test issue actions', () => {

  it('should create an action with SET_ISSUES type', () => {
    const payload: IssueSummary[] = ISSUE_LIST
    const expectation = {
      type: SET_ISSUES,
      payload: payload
    }

    expect(setIssues(payload)).toEqual(expectation)
  })

  it('should create an action with SET_SELECTED_ISSUE type', () => {
    const payload: Issue = ISSUE_SELECTED
    const expectation1 = {
      type: SET_SELECTED_ISSUE,
      payload: payload
    }
    const expectation2 = {
      type: SET_SELECTED_ISSUE,
      payload: undefined
    }

    expect(setSelectedIssue(payload)).toEqual(expectation1)
    expect(setSelectedIssue(undefined)).toEqual(expectation2)
  })

  it('should create an action with SET_ISSUE_LOADING type', () => {
    const payload = false
    const expectation = {
      type: SET_ISSUE_LOADING,
      payload: payload
    }

    expect(setIssueLoading(payload)).toEqual(expectation)
  })

})
