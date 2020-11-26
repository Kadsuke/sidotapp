export interface ITypeIntervention {
  id?: number;
  libelle?: string;
}

export class TypeIntervention implements ITypeIntervention {
  constructor(public id?: number, public libelle?: string) {}
}
