import { ICaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

export interface ITypeOuvrage {
  id?: number;
  libelle?: string;
  caracteristiqueOuvrages?: ICaracteristiqueOuvrage[];
  categorieressourcesId?: number;
  caracteristiqueouvrageId?: number;
}

export class TypeOuvrage implements ITypeOuvrage {
  constructor(
    public id?: number,
    public libelle?: string,
    public caracteristiqueOuvrages?: ICaracteristiqueOuvrage[],
    public categorieressourcesId?: number,
    public caracteristiqueouvrageId?: number
  ) {}
}
