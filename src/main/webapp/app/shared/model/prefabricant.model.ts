import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

export interface IPrefabricant {
  id?: number;
  libelle?: string;
  geuAaOuvrages?: IGeuAaOuvrage[];
}

export class Prefabricant implements IPrefabricant {
  constructor(public id?: number, public libelle?: string, public geuAaOuvrages?: IGeuAaOuvrage[]) {}
}
