import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface ISourceApprovEp {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class SourceApprovEp implements ISourceApprovEp {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
