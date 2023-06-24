import {Category} from "./Category";
import {IssueModelField} from "./IssueModelField";
import {IssueModelInfo} from "./IssueModelInfo";

export class IssueModel extends IssueModelInfo {
  private _fields: IssueModelField[]

  constructor(id: string, name: string, shortDescription: string, description: string, category: Category, fields: IssueModelField[]) {
    super(id, name, shortDescription, description, category);
    this._fields = fields;
  }

  get fields(): IssueModelField[] {
    return this._fields;
  }

  set fields(value: IssueModelField[]) {
    this._fields = value;
  }
}
