import {ISSUE_MODELS_URL, ISSUES_URL} from "../config/constants";
import {IssueModel} from "../model/issues/IssueModel";
import {Category} from "../model/issues/Category";
import {IssueModelInfo} from "../model/issues/IssueModelInfo";
import {IssueModelField} from "../model/issues/IssueModelField";
import {mapIssue, mapIssueModelInfo} from "../utils/mappers";
import {Issue} from "../model/issues/Issue";
import {IssueField} from "../model/issues/IssueField";

const fetchIssueModel = async (id: string): Promise<IssueModel> => {
  const url = `${ISSUE_MODELS_URL}/${id}`
  console.log('api', `request: ${url}`)
  const response = await fetch(url)
  const json = await response.json()

  //@ts-ignore
  const issueFields = json['fields'].map(field => new IssueModelField(field['title'], field['description'], field['required']))
  return new IssueModel(
    json['id'],
    json['name'],
    json['shortDescription'],
    json['description'],
    new Category(json['category']['name']),
    issueFields
  )
}

const fetchIssueModels = async (): Promise<IssueModelInfo[]> => {
  console.log('api', `request: ${ISSUE_MODELS_URL}`)
  const response = await fetch(ISSUE_MODELS_URL)
  if (response.status !== 200) return []
  const json = await response.json()

  //@ts-ignore
  return json.map(elt => mapIssueModelInfo(elt))
}

const createIssue = async (title: string, author: string, model: IssueModel, fields: IssueField[]): Promise<Issue | undefined> => {
  const body = {
    title: title,
    author: author,
    createdAt: new Date(),
    model: model.id,
    fields: fields.map(field => ({title: field.title, value: field.value}))
  }

  const options: RequestInit = {
    body: JSON.stringify(body),
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  }

  console.log('api', `request: ${ISSUES_URL}`)
  const response = await fetch(ISSUES_URL, options)
  if(response.status !== 200) return

  const json = await response.json()
  console.log(json)
  return mapIssue(json)
}

export {
  fetchIssueModel,
  fetchIssueModels,
  createIssue
}
