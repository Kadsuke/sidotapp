export interface IEtatEquipement {
  id?: number;
  libelle?: string;
}

export class EtatEquipement implements IEtatEquipement {
  constructor(public id?: number, public libelle?: string) {}
}
