export interface IRefMois {
  id?: number;
  libelle?: string;
}

export class RefMois implements IRefMois {
  constructor(public id?: number, public libelle?: string) {}
}
