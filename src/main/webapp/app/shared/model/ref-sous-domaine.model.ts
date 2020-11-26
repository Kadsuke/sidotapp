export interface IRefSousDomaine {
  id?: number;
  libelle?: string;
}

export class RefSousDomaine implements IRefSousDomaine {
  constructor(public id?: number, public libelle?: string) {}
}
