import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuRealisation } from 'app/shared/model/geu-realisation.model';

@Component({
  selector: 'jhi-geu-realisation-detail',
  templateUrl: './geu-realisation-detail.component.html',
})
export class GeuRealisationDetailComponent implements OnInit {
  geuRealisation: IGeuRealisation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuRealisation }) => (this.geuRealisation = geuRealisation));
  }

  previousState(): void {
    window.history.back();
  }
}
