export interface IBailleur {
  id?: number;
  libelle?: string;
  responsbale?: string;
  contact?: string;
}

export class Bailleur implements IBailleur {
  constructor(public id?: number, public libelle?: string, public responsbale?: string, public contact?: string) {}
}
