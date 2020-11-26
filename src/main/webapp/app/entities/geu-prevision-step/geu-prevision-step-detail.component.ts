import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';

@Component({
  selector: 'jhi-geu-prevision-step-detail',
  templateUrl: './geu-prevision-step-detail.component.html',
})
export class GeuPrevisionSTEPDetailComponent implements OnInit {
  geuPrevisionSTEP: IGeuPrevisionSTEP | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuPrevisionSTEP }) => (this.geuPrevisionSTEP = geuPrevisionSTEP));
  }

  previousState(): void {
    window.history.back();
  }
}
