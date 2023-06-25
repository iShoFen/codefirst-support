export class IssueModelField {
  private _title: string
  private _description: string
  private _required: boolean

  constructor(title: string, description: string, required: boolean) {
    this._title = title;
    this._description = description;
    this._required = required;
  }

  get title(): string {
    return this._title;
  }

  set title(value: string) {
    this._title = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get required(): boolean {
    return this._required;
  }

  set required(value: boolean) {
    this._required = value;
  }
}
