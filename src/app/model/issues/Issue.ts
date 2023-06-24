import {IssueStatus} from "./IssueStatus";
import {Category} from "./Category";
import {IssueModelInfo} from "./IssueModelInfo";
import {Comment} from "./Comment";
import {IssueField} from "./IssueField";
import {IssueModelShortInfo} from "./IssueModelShort";

export class Issue {
  private _id: string
  private _title: string
  private _author: string
  private _createdAt: Date
  private _status: IssueStatus
  private _category: Category
  private _model: IssueModelShortInfo
  private _comments: Comment[]
  private _fields: IssueField[]

  constructor(id: string, title: string, author: string, createdAt: Date, status: IssueStatus, category: Category, model: IssueModelShortInfo, comments: Comment[], fields: IssueField[]) {
    this._id = id;
    this._title = title;
    this._author = author;
    this._createdAt = createdAt;
    this._status = status;
    this._category = category;
    this._model = model;
    this._comments = comments;
    this._fields = fields;
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get author(): string {
    return this._author;
  }

  set author(value: string) {
    this._author = value;
  }

  get createdAt(): Date {
    return this._createdAt;
  }

  set createdAt(value: Date) {
    this._createdAt = value;
  }

  get status(): IssueStatus {
    return this._status;
  }

  set status(value: IssueStatus) {
    this._status = value;
  }

  get category(): Category {
    return this._category;
  }

  set category(value: Category) {
    this._category = value;
  }

  get model(): IssueModelShortInfo {
    return this._model;
  }

  set model(value: IssueModelShortInfo) {
    this._model = value;
  }

  get comments(): Comment[] {
    return this._comments;
  }

  set comments(value: Comment[]) {
    this._comments = value;
  }

  get fields(): IssueField[] {
    return this._fields;
  }

  set fields(value: IssueField[]) {
    this._fields = value;
  }
}
