import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuSTEP } from 'app/shared/model/geu-step.model';

@Component({
  selector: 'jhi-geu-step-detail',
  templateUrl: './geu-step-detail.component.html',
})
export class GeuSTEPDetailComponent implements OnInit {
  geuSTEP: IGeuSTEP | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuSTEP }) => (this.geuSTEP = geuSTEP));
  }

  previousState(): void {
    window.history.back();
  }
}
