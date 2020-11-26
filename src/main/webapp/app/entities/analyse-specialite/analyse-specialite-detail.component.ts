import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';

@Component({
  selector: 'jhi-analyse-specialite-detail',
  templateUrl: './analyse-specialite-detail.component.html',
})
export class AnalyseSpecialiteDetailComponent implements OnInit {
  analyseSpecialite: IAnalyseSpecialite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ analyseSpecialite }) => (this.analyseSpecialite = analyseSpecialite));
  }

  previousState(): void {
    window.history.back();
  }
}
