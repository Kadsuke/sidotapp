import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAnalyseParametre } from 'app/shared/model/analyse-parametre.model';

@Component({
  selector: 'jhi-analyse-parametre-detail',
  templateUrl: './analyse-parametre-detail.component.html',
})
export class AnalyseParametreDetailComponent implements OnInit {
  analyseParametre: IAnalyseParametre | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ analyseParametre }) => (this.analyseParametre = analyseParametre));
  }

  previousState(): void {
    window.history.back();
  }
}
