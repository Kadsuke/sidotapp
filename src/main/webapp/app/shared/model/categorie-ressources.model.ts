import { ITypeOuvrage } from 'app/shared/model/type-ouvrage.model';

export interface ICategorieRessources {
  id?: number;
  libelle?: string;
  typeOuvrages?: ITypeOuvrage[];
}

export class CategorieRessources implements ICategorieRessources {
  constructor(public id?: number, public libelle?: string, public typeOuvrages?: ITypeOuvrage[]) {}
}
