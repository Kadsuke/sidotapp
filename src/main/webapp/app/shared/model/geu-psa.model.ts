import { Moment } from 'moment';

export interface IGeuPSA {
  id?: number;
  dateElaboration?: Moment;
  dateMiseEnOeuvre?: Moment;
  geocommuneId?: number;
}

export class GeuPSA implements IGeuPSA {
  constructor(public id?: number, public dateElaboration?: Moment, public dateMiseEnOeuvre?: Moment, public geocommuneId?: number) {}
}
