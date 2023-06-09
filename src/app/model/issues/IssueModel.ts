import {Category} from "./Category";
import {IssueModelField} from "./IssueModelField";

export class IssueModel extends IssueModelField {
  private _id: string
  private _category: Category
  private _fields: IssueModelField[]

  constructor(title: string, description: string, required: boolean, id: string, category: Category, fields: IssueModelField[]) {
    super(title, description, required);
    this._id = id;
    this._category = category;
    this._fields = fields;
  }

  get id(): string {
    return this._id;
  }

  set id(value: string) {
    this._id = value;
  }

  get category(): Category {
    return this._category;
  }

  set category(value: Category) {
    this._category = value;
  }

  get fields(): IssueModelField[] {
    return this._fields;
  }

  set fields(value: IssueModelField[]) {
    this._fields = value;
  }
}
