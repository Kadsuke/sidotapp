export interface IGeuRaccordement {
  id?: number;
  numAbonnement?: number;
  nom?: string;
  prenom?: string;
  adresse?: string;
  proprietaireParacelle?: string;
  entreprise?: string;
  otherUsage?: string;
  geoparcelleId?: number;
  agentId?: number;
  tacheronsId?: number;
  geuusageId?: number;
}

export class GeuRaccordement implements IGeuRaccordement {
  constructor(
    public id?: number,
    public numAbonnement?: number,
    public nom?: string,
    public prenom?: string,
    public adresse?: string,
    public proprietaireParacelle?: string,
    public entreprise?: string,
    public otherUsage?: string,
    public geoparcelleId?: number,
    public agentId?: number,
    public tacheronsId?: number,
    public geuusageId?: number
  ) {}
}
