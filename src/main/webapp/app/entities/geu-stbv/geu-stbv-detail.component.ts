import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuSTBV } from 'app/shared/model/geu-stbv.model';

@Component({
  selector: 'jhi-geu-stbv-detail',
  templateUrl: './geu-stbv-detail.component.html',
})
export class GeuSTBVDetailComponent implements OnInit {
  geuSTBV: IGeuSTBV | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuSTBV }) => (this.geuSTBV = geuSTBV));
  }

  previousState(): void {
    window.history.back();
  }
}
