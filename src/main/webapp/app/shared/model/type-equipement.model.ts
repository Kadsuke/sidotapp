export interface ITypeEquipement {
  id?: number;
  libelle?: string;
}

export class TypeEquipement implements ITypeEquipement {
  constructor(public id?: number, public libelle?: string) {}
}
