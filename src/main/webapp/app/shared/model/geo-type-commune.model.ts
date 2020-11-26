import { IGeoCommune } from 'app/shared/model/geo-commune.model';

export interface IGeoTypeCommune {
  id?: number;
  libelle?: string;
  geoCommunes?: IGeoCommune[];
}

export class GeoTypeCommune implements IGeoTypeCommune {
  constructor(public id?: number, public libelle?: string, public geoCommunes?: IGeoCommune[]) {}
}
