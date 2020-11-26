export interface ITypePlainte {
  id?: number;
  libelle?: string;
}

export class TypePlainte implements ITypePlainte {
  constructor(public id?: number, public libelle?: string) {}
}
