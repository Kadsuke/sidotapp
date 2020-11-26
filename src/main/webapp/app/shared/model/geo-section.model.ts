import { IGeoLot } from 'app/shared/model/geo-lot.model';

export interface IGeoSection {
  id?: number;
  libelle?: string;
  geoLots?: IGeoLot[];
  geosecteurId?: number;
}

export class GeoSection implements IGeoSection {
  constructor(public id?: number, public libelle?: string, public geoLots?: IGeoLot[], public geosecteurId?: number) {}
}
