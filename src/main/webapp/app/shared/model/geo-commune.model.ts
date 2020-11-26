import { IGeoLocalite } from 'app/shared/model/geo-localite.model';
import { IGeuPSA } from 'app/shared/model/geu-psa.model';

export interface IGeoCommune {
  id?: number;
  libelle?: string;
  geoLocalites?: IGeoLocalite[];
  geuPSAS?: IGeuPSA[];
  geoprovinceId?: number;
  geotypecommuneId?: number;
}

export class GeoCommune implements IGeoCommune {
  constructor(
    public id?: number,
    public libelle?: string,
    public geoLocalites?: IGeoLocalite[],
    public geuPSAS?: IGeuPSA[],
    public geoprovinceId?: number,
    public geotypecommuneId?: number
  ) {}
}
