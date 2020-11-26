import { Moment } from 'moment';

export interface IGeuPrevisionSTEP {
  id?: number;
  datePrevStep?: Moment;
  volumePrevStep?: string;
  geustepId?: number;
}

export class GeuPrevisionSTEP implements IGeuPrevisionSTEP {
  constructor(public id?: number, public datePrevStep?: Moment, public volumePrevStep?: string, public geustepId?: number) {}
}
