import { ITypeOuvrage } from 'app/shared/model/type-ouvrage.model';

export interface ICaracteristiqueOuvrage {
  id?: number;
  libelle?: string;
  unite?: string;
  typeOuvrages?: ITypeOuvrage[];
  typeouvrageId?: number;
}

export class CaracteristiqueOuvrage implements ICaracteristiqueOuvrage {
  constructor(
    public id?: number,
    public libelle?: string,
    public unite?: string,
    public typeOuvrages?: ITypeOuvrage[],
    public typeouvrageId?: number
  ) {}
}
