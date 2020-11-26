import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface ITypeHabitation {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class TypeHabitation implements ITypeHabitation {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
