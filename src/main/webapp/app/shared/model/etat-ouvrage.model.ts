export interface IEtatOuvrage {
  id?: number;
  libelle?: string;
}

export class EtatOuvrage implements IEtatOuvrage {
  constructor(public id?: number, public libelle?: string) {}
}
