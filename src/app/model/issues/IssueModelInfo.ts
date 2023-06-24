import {Category} from "./Category";
import {IssueModelShortInfo} from "./IssueModelShort";

export class IssueModelInfo extends IssueModelShortInfo{
  private readonly _id: string
  private _category: Category

  constructor(id: string, name: string, shortDescription: string, description: string, category: Category) {
    super(name, shortDescription, description)
    this._id = id;
    this._category = category;
  }

  get id(): string {
    return this._id;
  }

  get category(): Category {
    return this._category;
  }

  set category(value: Category) {
    this._category = value;
  }
}
