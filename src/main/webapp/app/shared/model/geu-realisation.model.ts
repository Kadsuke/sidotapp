export interface IGeuRealisation {
  id?: number;
  nbRealisation?: number;
  geuaaouvrageId?: number;
}

export class GeuRealisation implements IGeuRealisation {
  constructor(public id?: number, public nbRealisation?: number, public geuaaouvrageId?: number) {}
}
