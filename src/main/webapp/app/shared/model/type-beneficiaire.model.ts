export interface ITypeBeneficiaire {
  id?: number;
  libelle?: string;
}

export class TypeBeneficiaire implements ITypeBeneficiaire {
  constructor(public id?: number, public libelle?: string) {}
}
