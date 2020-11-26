export interface IAnalyseParametre {
  id?: number;
  libelle?: string;
  analysespecialiteId?: number;
}

export class AnalyseParametre implements IAnalyseParametre {
  constructor(public id?: number, public libelle?: string, public analysespecialiteId?: number) {}
}
