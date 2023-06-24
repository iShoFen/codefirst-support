import {Dispatch} from "redux";
import {setIssueLoading, setIssues, setSelectedIssue} from "../actions/issueAction";
import {ISSUES_URL} from "../../config/constants";
import {
  mapIssue,
  mapIssueSummary,
} from "../../utils/mappers";


export const getIssues = () => {
  return async (dispatch: Dispatch) => {
    try {
      dispatch(setIssueLoading(true))
      console.log('api', `request: ${ISSUES_URL}`)
      const promise = await fetch(ISSUES_URL)
      const json = await promise.json()
      //@ts-ignore
      const issues = json.map((elt) => mapIssueSummary(elt))
      dispatch(setIssues(issues))
    } catch (error) {
      console.log('Error---------', error);
      // @ts-ignore
    } finally {
      dispatch(setIssueLoading(false))
    }
  }
}

export const getIssue = (id: string) => {
  return async (dispatch: Dispatch) => {
    try {
      dispatch(setIssueLoading(true))
      const url = `${ISSUES_URL}/${id}`
      console.log('api', `request: ${url}`)
      const promise = await fetch(url)
      const json = await promise.json()

      const issue = mapIssue(json)
      dispatch(setSelectedIssue(issue))
    } catch (error) {
      console.log('Error---------', error);
    } finally {
      dispatch(setIssueLoading(false))
    }
  }
}
