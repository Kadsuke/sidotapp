export interface IRefAnnee {
  id?: number;
  libelle?: string;
  previsionAssainissementAuId?: number;
  previsionAssainissementColId?: number;
  previsionPsaId?: number;
}

export class RefAnnee implements IRefAnnee {
  constructor(
    public id?: number,
    public libelle?: string,
    public previsionAssainissementAuId?: number,
    public previsionAssainissementColId?: number,
    public previsionPsaId?: number
  ) {}
}
