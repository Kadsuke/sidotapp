export interface ITypeAnalyse {
  id?: number;
  libelle?: string;
}

export class TypeAnalyse implements ITypeAnalyse {
  constructor(public id?: number, public libelle?: string) {}
}
