import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';

@Component({
  selector: 'jhi-geu-realisation-stbv-detail',
  templateUrl: './geu-realisation-stbv-detail.component.html',
})
export class GeuRealisationSTBVDetailComponent implements OnInit {
  geuRealisationSTBV: IGeuRealisationSTBV | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuRealisationSTBV }) => (this.geuRealisationSTBV = geuRealisationSTBV));
  }

  previousState(): void {
    window.history.back();
  }
}
