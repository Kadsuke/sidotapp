import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface IMacon {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class Macon implements IMacon {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
