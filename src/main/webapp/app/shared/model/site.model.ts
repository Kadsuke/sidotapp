import { IAgent } from 'app/shared/model/agent.model';

export interface ISite {
  id?: number;
  libelle?: string;
  responsable?: string;
  contact?: string;
  agents?: IAgent[];
  centreId?: number;
}

export class Site implements ISite {
  constructor(
    public id?: number,
    public libelle?: string,
    public responsable?: string,
    public contact?: string,
    public agents?: IAgent[],
    public centreId?: number
  ) {}
}
