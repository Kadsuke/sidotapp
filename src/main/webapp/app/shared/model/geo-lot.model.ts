import { IGeoParcelle } from 'app/shared/model/geo-parcelle.model';

export interface IGeoLot {
  id?: number;
  libelle?: string;
  geoParcelles?: IGeoParcelle[];
  geosectionId?: number;
}

export class GeoLot implements IGeoLot {
  constructor(public id?: number, public libelle?: string, public geoParcelles?: IGeoParcelle[], public geosectionId?: number) {}
}
