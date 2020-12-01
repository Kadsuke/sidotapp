export interface IPrevisionAssainissementCol {
  id?: number;
  nbStep?: number;
  nbStbv?: number;
  reseaux?: number;
  nbRaccordement?: number;
  refanneeId?: number;
  centreId?: number;
}

export class PrevisionAssainissementCol implements IPrevisionAssainissementCol {
  constructor(
    public id?: number,
    public nbStep?: number,
    public nbStbv?: number,
    public reseaux?: number,
    public nbRaccordement?: number,
    public refanneeId?: number,
    public centreId?: number
  ) {}
}
