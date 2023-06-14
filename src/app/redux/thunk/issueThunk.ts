import {Issue} from "../../model/issues/Issue";
import {Dispatch} from "redux";
import {setIssues, setSelectedIssue} from "../actions/issueAction";


export const getIssues = () => {
  return async (dispatch: Dispatch) => {
    try {
      const promise = await fetch('https://codefirst.iut.uca.fr')
      const json = await promise.json()
      //@ts-ignore
      const issues = json.map((elt) => new Issue(elt['id'], elt['title'], elt['author'], elt['created_at'], elt['status'], elt['category'], elt['model'], elt['comments'], elt['fields']))
      dispatch(setIssues(issues))
    } catch (error) {
      console.log('Error---------', error);
    }
  }
}

export const getIssue = (id: string) => {
  return async (dispatch: Dispatch) => {
    try {
      const promise = await fetch('https://codefirst.iut.uca.fr')
      const json = await promise.json()
      //@ts-ignore
      const issue = new Issue(json['id'], json['title'], json['author'], json['created_at'], json['status'], json['category'], json['model'], json['comments'], json['fields'])
      dispatch(setSelectedIssue(issue))
    } catch (error) {
      console.log('Error---------', error);
    }
  }
}
