import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

@Component({
  selector: 'jhi-geu-prevision-stbv-detail',
  templateUrl: './geu-prevision-stbv-detail.component.html',
})
export class GeuPrevisionSTBVDetailComponent implements OnInit {
  geuPrevisionSTBV: IGeuPrevisionSTBV | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuPrevisionSTBV }) => (this.geuPrevisionSTBV = geuPrevisionSTBV));
  }

  previousState(): void {
    window.history.back();
  }
}
