import { IGeoSection } from 'app/shared/model/geo-section.model';

export interface IGeoSecteur {
  id?: number;
  libelle?: string;
  geoSections?: IGeoSection[];
  geolocaliteId?: number;
}

export class GeoSecteur implements IGeoSecteur {
  constructor(public id?: number, public libelle?: string, public geoSections?: IGeoSection[], public geolocaliteId?: number) {}
}
