import {Question} from "./Question";

export class Survey {
  private _id: string
  private _title: string
  private _createdAt: Date
  private _publishedAt: Date
  private _endAt: Date
  private _description: string
  private _questions: Question[]

  constructor(id: string, title: string, createdAt: Date, publishedAt: Date, endAt: Date, description: string, questions: Question[]) {
    this._id = id;
    this._title = title;
    this._createdAt = createdAt;
    this._publishedAt = publishedAt;
    this._endAt = endAt;
    this._description = description;
    this._questions = questions;
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

  get createdAt(): Date {
    return this._createdAt;
  }

  set createdAt(value: Date) {
    this._createdAt = value;
  }

  get publishedAt(): Date {
    return this._publishedAt;
  }

  set publishedAt(value: Date) {
    this._publishedAt = value;
  }

  get endAt(): Date {
    return this._endAt;
  }

  set endAt(value: Date) {
    this._endAt = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get questions(): Question[] {
    return this._questions;
  }

  set questions(value: Question[]) {
    this._questions = value;
  }
}
