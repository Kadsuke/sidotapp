import { IGeoSecteur } from 'app/shared/model/geo-secteur.model';

export interface IGeoLocalite {
  id?: number;
  libelle?: string;
  geoSecteurs?: IGeoSecteur[];
  geocommuneId?: number;
}

export class GeoLocalite implements IGeoLocalite {
  constructor(public id?: number, public libelle?: string, public geoSecteurs?: IGeoSecteur[], public geocommuneId?: number) {}
}
