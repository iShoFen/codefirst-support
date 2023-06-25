import {Question} from "./Question";

export class Feedback {
  private _id: string
  private _surveyId: string
  private _createdAt: Date
  private _author: string
  private _question: Question
  private _answers: string[]

  constructor(id: string, surveyId: string, createdAt: Date, author: string, question: Question, answers: string[]) {
    this._id = id;
    this._surveyId = surveyId;
    this._createdAt = createdAt;
    this._author = author;
    this._question = question;
    this._answers = answers;
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get surveyId(): string {
    return this._surveyId;
  }

  set surveyId(value: string) {
    this._surveyId = value;
  }

  get createdAt(): Date {
    return this._createdAt;
  }

  set createdAt(value: Date) {
    this._createdAt = value;
  }

  get author(): string {
    return this._author;
  }

  set author(value: string) {
    this._author = value;
  }

  get question(): Question {
    return this._question;
  }

  set question(value: Question) {
    this._question = value;
  }

  get answers(): string[] {
    return this._answers;
  }

  set answers(value: string[]) {
    this._answers = value;
  }
}
