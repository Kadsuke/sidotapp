export interface IGeuRealisationSTBV {
  id?: number;
  nbCamions?: string;
  volume?: string;
  energie?: string;
  geustbvId?: number;
}

export class GeuRealisationSTBV implements IGeuRealisationSTBV {
  constructor(public id?: number, public nbCamions?: string, public volume?: string, public energie?: string, public geustbvId?: number) {}
}
