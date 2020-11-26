export interface IRefAnnee {
  id?: number;
  libelle?: string;
}

export class RefAnnee implements IRefAnnee {
  constructor(public id?: number, public libelle?: string) {}
}
