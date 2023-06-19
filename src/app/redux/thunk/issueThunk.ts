import {Issue} from "../../model/issues/Issue";
import {Dispatch} from "redux";
import {setIssueLoading, setIssues, setSelectedIssue} from "../actions/issueAction";
import {ISSUES_URL} from "../../config/constants";
import {IssueStatus} from "../../model/issues/IssueStatus";
import {IssueSummary} from "../../model/issues/IssueSummary";
import {Category} from "../../model/issues/Category";
import {IssueModelInfo} from "../../model/issues/IssueModelInfo";
import {Comment} from "../../model/issues/Comment";
import {IssueField} from "../../model/issues/IssueField";

function mapStatus(rawStatus: string): IssueStatus {
  let status: IssueStatus = IssueStatus.UNKNOWN
  if (rawStatus === 'open') {
    status = IssueStatus.OPEN
  } else if (rawStatus === 'closed') {
    status = IssueStatus.CLOSED
  }
  return status
}

function mapIssueModelInfo(rawModel: {name: string, shortDescription: string, description: string}): IssueModelInfo {
  return new IssueModelInfo(
    rawModel.name,
    rawModel.shortDescription,
    rawModel.description
  )
}

function mapComments(rawComments: any): Comment[] {
  return rawComments.map((comment: {createdAt: Date, author: string, content: string}) => new Comment(new Date(comment.createdAt), comment.author, comment.content))
}

function mapFields(rawFields: any): IssueField[] {
  return rawFields.map((field: {title: string, description: string, required: boolean, value: string}) => new IssueField(field.title, field.description, field.required, field.value))
}

export const getIssues = () => {
  return async (dispatch: Dispatch) => {
    try {
      dispatch(setIssueLoading(true))
      console.log('api', `request: ${ISSUES_URL}`)
      const promise = await fetch(ISSUES_URL)
      const json = await promise.json()
      //@ts-ignore
      const issues = json.map((elt) => {
        const id = elt['id']
        const title = elt['title']
        const author = elt['author']
        const createdAt = new Date(elt['createdAt'])
        const status = mapStatus(elt['status'])
        const category = new Category(elt['category'].name)

        return new IssueSummary(id, title, author, createdAt, status, category)
      })
      dispatch(setIssues(issues))
    } catch (error) {
      console.log('Error---------', error);
      // @ts-ignore
      dispatch(setError(error.toString()))
    } finally {
      dispatch(setIssueLoading(false))
    }
  }
}

export const getIssue = (id: string) => {
  return async (dispatch: Dispatch) => {
    try {
      const url = `${ISSUES_URL}/${id}`
      console.log('api', `request: ${url}`)
      const promise = await fetch(url)
      const json = await promise.json()

      const issueId = json['id']
      const title = json['title']
      const author = json['author']
      const createdAt = new Date(json['createdAt'])
      const status = mapStatus(json['status'])
      const category = new Category(json['category'].name)
      const model: IssueModelInfo = mapIssueModelInfo(json['model'])
      const comments = mapComments(json['comments'])
      const fields = mapFields(json['fields'])
      //@ts-ignore
      const issue = new Issue(issueId, title, author, createdAt, status, category, model, comments, fields)
      dispatch(setSelectedIssue(issue))
    } catch (error) {
      console.log('Error---------', error);
    }
  }
}
