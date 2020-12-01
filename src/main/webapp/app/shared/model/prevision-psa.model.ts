export interface IPrevisionPsa {
  id?: number;
  elabore?: number;
  miseEnOeuvre?: number;
  centreId?: number;
  refAnneeId?: number;
}

export class PrevisionPsa implements IPrevisionPsa {
  constructor(
    public id?: number,
    public elabore?: number,
    public miseEnOeuvre?: number,
    public centreId?: number,
    public refAnneeId?: number
  ) {}
}
