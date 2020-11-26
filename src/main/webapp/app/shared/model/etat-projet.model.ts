export interface IEtatProjet {
  id?: number;
  libelle?: string;
}

export class EtatProjet implements IEtatProjet {
  constructor(public id?: number, public libelle?: string) {}
}
