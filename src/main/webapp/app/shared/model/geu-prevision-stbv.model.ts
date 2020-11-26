export interface IGeuPrevisionSTBV {
  id?: number;
  nbCamions?: string;
  volume?: string;
  energie?: string;
  geustbvId?: number;
}

export class GeuPrevisionSTBV implements IGeuPrevisionSTBV {
  constructor(public id?: number, public nbCamions?: string, public volume?: string, public energie?: string, public geustbvId?: number) {}
}
