import {QuestionType} from "./QuestionType";

export class QuestionInfo {
  protected _title: string
  protected _type: QuestionType

  constructor(title: string, type: QuestionType) {
    this._title = title;
    this._type = type;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get type(): QuestionType {
    return this._type;
  }

  set type(value: QuestionType) {
    this._type = value;
  }
}
