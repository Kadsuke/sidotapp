export interface IEtatProgram {
  id?: number;
  libelle?: string;
}

export class EtatProgram implements IEtatProgram {
  constructor(public id?: number, public libelle?: string) {}
}
