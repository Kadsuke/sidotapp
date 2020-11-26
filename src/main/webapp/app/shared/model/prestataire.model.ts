export interface IPrestataire {
  id?: number;
  libelle?: string;
  responsable?: string;
  contact?: string;
}

export class Prestataire implements IPrestataire {
  constructor(public id?: number, public libelle?: string, public responsable?: string, public contact?: string) {}
}
