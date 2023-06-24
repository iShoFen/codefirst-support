import {IssueModelField} from "./IssueModelField";

export class IssueField extends IssueModelField{
  private _value: string

  constructor(title: string, description: string, required: boolean, value: string) {
    super(title, description, required);
    this._value = value;
  }

  get value(): string {
    return this._value;
  }

  set value(value: string) {
    this._value = value;
  }
}
