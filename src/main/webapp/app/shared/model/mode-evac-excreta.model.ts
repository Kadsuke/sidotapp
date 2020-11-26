import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface IModeEvacExcreta {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class ModeEvacExcreta implements IModeEvacExcreta {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
