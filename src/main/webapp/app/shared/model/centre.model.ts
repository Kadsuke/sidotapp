import { ISite } from 'app/shared/model/site.model';

export interface ICentre {
  id?: number;
  libelle?: string;
  responsable?: string;
  contact?: string;
  sites?: ISite[];
  previsionAssainissementAuId?: number;
  previsionAssainissementColId?: number;
  previsionPsaId?: number;
  centreregroupementId?: number;
}

export class Centre implements ICentre {
  constructor(
    public id?: number,
    public libelle?: string,
    public responsable?: string,
    public contact?: string,
    public sites?: ISite[],
    public previsionAssainissementAuId?: number,
    public previsionAssainissementColId?: number,
    public previsionPsaId?: number,
    public centreregroupementId?: number
  ) {}
}
