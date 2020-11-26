import { ICentreRegroupement } from 'app/shared/model/centre-regroupement.model';

export interface IDirectionRegionale {
  id?: number;
  libelle?: string;
  responsable?: string;
  contact?: string;
  centreRegroupements?: ICentreRegroupement[];
}

export class DirectionRegionale implements IDirectionRegionale {
  constructor(
    public id?: number,
    public libelle?: string,
    public responsable?: string,
    public contact?: string,
    public centreRegroupements?: ICentreRegroupement[]
  ) {}
}
