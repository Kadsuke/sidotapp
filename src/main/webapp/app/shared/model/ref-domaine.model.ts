export interface IRefDomaine {
  id?: number;
  libelle?: string;
}

export class RefDomaine implements IRefDomaine {
  constructor(public id?: number, public libelle?: string) {}
}
