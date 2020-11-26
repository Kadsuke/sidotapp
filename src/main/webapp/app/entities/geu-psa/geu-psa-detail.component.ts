import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuPSA } from 'app/shared/model/geu-psa.model';

@Component({
  selector: 'jhi-geu-psa-detail',
  templateUrl: './geu-psa-detail.component.html',
})
export class GeuPSADetailComponent implements OnInit {
  geuPSA: IGeuPSA | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuPSA }) => (this.geuPSA = geuPSA));
  }

  previousState(): void {
    window.history.back();
  }
}
