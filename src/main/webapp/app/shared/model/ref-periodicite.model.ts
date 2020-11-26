export interface IRefPeriodicite {
  id?: number;
  libelle?: string;
}

export class RefPeriodicite implements IRefPeriodicite {
  constructor(public id?: number, public libelle?: string) {}
}
