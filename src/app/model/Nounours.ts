export class Nounours {
  private _name: string
  private _nbPoils: number
  private _age: number
  private _image: string

  constructor(name: string, nbPoils: number, age: number, image: string) {
    this._name = name;
    this._nbPoils = nbPoils;
    this._age = age;
    this._image = image;
  }


  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get nbPoils(): number {
    return this._nbPoils;
  }

  set nbPoils(value: number) {
    this._nbPoils = value;
  }

  get age(): number {
    return this._age;
  }

  set age(value: number) {
    this._age = value;
  }

  get image(): string {
    return this._image;
  }

  set image(value: string) {
    this._image = value;
  }
}
