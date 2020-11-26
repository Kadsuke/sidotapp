import { ICentre } from 'app/shared/model/centre.model';

export interface ICentreRegroupement {
  id?: number;
  libelle?: string;
  responsable?: string;
  contact?: string;
  centres?: ICentre[];
  directionregionaleId?: number;
}

export class CentreRegroupement implements ICentreRegroupement {
  constructor(
    public id?: number,
    public libelle?: string,
    public responsable?: string,
    public contact?: string,
    public centres?: ICentre[],
    public directionregionaleId?: number
  ) {}
}
