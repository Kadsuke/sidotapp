import { IGeoProvince } from 'app/shared/model/geo-province.model';

export interface IGeoRegion {
  id?: number;
  libelle?: string;
  geoProvinces?: IGeoProvince[];
}

export class GeoRegion implements IGeoRegion {
  constructor(public id?: number, public libelle?: string, public geoProvinces?: IGeoProvince[]) {}
}
