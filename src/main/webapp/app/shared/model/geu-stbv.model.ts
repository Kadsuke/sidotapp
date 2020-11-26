import { IGeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';
import { IGeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

export interface IGeuSTBV {
  id?: number;
  libelStbv?: string;
  responsable?: string;
  contact?: string;
  geuRealisationSTBVS?: IGeuRealisationSTBV[];
  geuPrevisionSTBVS?: IGeuPrevisionSTBV[];
}

export class GeuSTBV implements IGeuSTBV {
  constructor(
    public id?: number,
    public libelStbv?: string,
    public responsable?: string,
    public contact?: string,
    public geuRealisationSTBVS?: IGeuRealisationSTBV[],
    public geuPrevisionSTBVS?: IGeuPrevisionSTBV[]
  ) {}
}
