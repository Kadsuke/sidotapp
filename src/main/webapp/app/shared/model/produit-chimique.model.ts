export interface IProduitChimique {
  id?: number;
  libelle?: string;
}

export class ProduitChimique implements IProduitChimique {
  constructor(public id?: number, public libelle?: string) {}
}
