export class Comment {
  private _createdAt: Date
  private _author: string
  private _content: string

  constructor(createdAt: Date, author: string, content: string) {
    this._createdAt = createdAt;
    this._author = author;
    this._content = content;
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

  get content(): string {
    return this._content;
  }

  set content(value: string) {
    this._content = value;
  }
}
