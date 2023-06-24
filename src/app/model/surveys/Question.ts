import {QuestionInfo} from "./QuestionInfo";
import {QuestionType} from "./QuestionType";

export class Question extends QuestionInfo {
  private _description: string
  private _choices: string[]


  constructor(title: string, type: QuestionType, description: string, choices: string[]) {
    super(title, type);
    this._description = description;
    this._choices = choices;
  }


  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get choices(): string[] {
    return this._choices;
  }

  set choices(value: string[]) {
    this._choices = value;
  }
}
