export interface IPrevisionAssainissementAu {
  id?: number;
  nbLatrine?: number;
  nbPuisard?: number;
  nbPublic?: number;
  nbScolaire?: number;
  centreDeSante?: number;
  population?: number;
  refanneeId?: number;
  centreId?: number;
}

export class PrevisionAssainissementAu implements IPrevisionAssainissementAu {
  constructor(
    public id?: number,
    public nbLatrine?: number,
    public nbPuisard?: number,
    public nbPublic?: number,
    public nbScolaire?: number,
    public centreDeSante?: number,
    public population?: number,
    public refanneeId?: number,
    public centreId?: number
  ) {}
}
