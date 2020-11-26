import { IGeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';

export interface IGeuSTEP {
  id?: number;
  libelSTEP?: string;
  responsable?: string;
  contact?: string;
  geuPrevisionSTEPS?: IGeuPrevisionSTEP[];
}

export class GeuSTEP implements IGeuSTEP {
  constructor(
    public id?: number,
    public libelSTEP?: string,
    public responsable?: string,
    public contact?: string,
    public geuPrevisionSTEPS?: IGeuPrevisionSTEP[]
  ) {}
}
