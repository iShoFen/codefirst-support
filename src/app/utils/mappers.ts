import {IssueStatus} from "../model/issues/IssueStatus";
import {IssueModelInfo} from "../model/issues/IssueModelInfo";
import {Category} from "../model/issues/Category";
import {Comment} from "../model/issues/Comment";
import {IssueField} from "../model/issues/IssueField";
import {Issue} from "../model/issues/Issue";
import {IssueSummary} from "../model/issues/IssueSummary";
import {IssueModelShortInfo} from "../model/issues/IssueModelShort";

function mapStatus(rawStatus: string): IssueStatus {
  let status: IssueStatus = IssueStatus.UNKNOWN
  if (rawStatus === 'open') {
    status = IssueStatus.OPEN
  } else if (rawStatus === 'closed') {
    status = IssueStatus.CLOSED
  }
  return status
}

function mapIssueModelShortInfo(rawModel: {
  name: string,
  shortDescription: string,
  description: string
}): IssueModelShortInfo {
  return new IssueModelShortInfo(
    rawModel['name'],
    rawModel['shortDescription'],
    rawModel['description']
  )
}

function mapIssueModelInfo(rawModel: {
  id: string,
  name: string,
  shortDescription: string,
  description: string,
  category: { name: string }
}): IssueModelInfo {
  return new IssueModelInfo(
    rawModel['id'],
    rawModel['name'],
    rawModel['shortDescription'],
    rawModel['description'],
    mapCategory(rawModel['category'])
  )
}

function mapComment(comment: { createdAt: Date, author: string, content: string }): Comment {
  return new Comment(new Date(comment.createdAt), comment.author, comment.content)
}

function mapComments(rawComments: any): Comment[] {
  return rawComments.map((comment: any) => mapComment(comment))
}

function mapFields(rawFields: any): IssueField[] {
  return rawFields.map((field: {
    title: string,
    description: string,
    required: boolean,
    value: string
  }) => new IssueField(field.title, field.description, field.required, field.value))
}

function mapCategory(rawCategory: { name: string }) {
  return new Category(rawCategory.name)
}

function mapIssue(rawIssue: any): Issue {
  const issueId = rawIssue['id'] as string
  const title = rawIssue['title'] as string
  const author = rawIssue['author'] as string
  const createdAt = new Date(rawIssue['created_at'])
  const status = mapStatus(rawIssue['status'])
  const category = mapCategory(rawIssue['category'])
  const model: IssueModelShortInfo = mapIssueModelShortInfo(rawIssue['model'])
  const comments = mapComments(rawIssue['comments'])
  const fields = mapFields(rawIssue['fields'])
  //@ts-ignore
  return new Issue(issueId, title, author, createdAt, status, category, model, comments, fields)
}

function mapIssueSummary(rawIssueSummary: any): IssueSummary {
  const id = rawIssueSummary['id'] as string
  const title = rawIssueSummary['title'] as string
  const author = rawIssueSummary['author'] as string
  const createdAt = new Date(rawIssueSummary['created_at'])
  const status = mapStatus(rawIssueSummary['status'])
  const category = new Category(rawIssueSummary['category'].name)

  return new IssueSummary(id, title, author, createdAt, status, category)
}

export {
  mapCategory,
  mapStatus,
  mapFields,
  mapIssueModelInfo,
  mapComment,
  mapComments,
  mapIssue,
  mapIssueSummary
}
