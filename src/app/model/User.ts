export class User {
  private _email: string
  private _image: string
  private _isAdmin: boolean;

  constructor(email: string, image: string, isAdmin: boolean) {
    this._email = email;
    this._image = image;
    this._isAdmin = isAdmin;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }


  get image(): string {
    return this._image;
  }

  set image(value: string) {
    this._image = value;
  }
  get isAdmin(): boolean {
    return this._isAdmin;
  }

  set isAdmin(value: boolean) {
    this._isAdmin = value;
  }
}
