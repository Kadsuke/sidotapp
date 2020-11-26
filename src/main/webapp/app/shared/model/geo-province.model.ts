import { IGeoCommune } from 'app/shared/model/geo-commune.model';

export interface IGeoProvince {
  id?: number;
  libelle?: string;
  geoCommunes?: IGeoCommune[];
  georegionId?: number;
}

export class GeoProvince implements IGeoProvince {
  constructor(public id?: number, public libelle?: string, public geoCommunes?: IGeoCommune[], public georegionId?: number) {}
}
