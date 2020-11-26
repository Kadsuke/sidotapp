import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface IModeEvacuationEauUsee {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class ModeEvacuationEauUsee implements IModeEvacuationEauUsee {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
