export class IssueModelInfo {
  private _name: string
  private _shortDescription: string
  private _description: string

  constructor(name: string, shortDescription: string, description: string) {
    this._name = name;
    this._shortDescription = shortDescription;
    this._description = description;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get shortDescription(): string {
    return this._shortDescription;
  }

  set shortDescription(value: string) {
    this._shortDescription = value;
  }

  get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }
}
