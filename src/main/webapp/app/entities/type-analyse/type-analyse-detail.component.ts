import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeAnalyse } from 'app/shared/model/type-analyse.model';

@Component({
  selector: 'jhi-type-analyse-detail',
  templateUrl: './type-analyse-detail.component.html',
})
export class TypeAnalyseDetailComponent implements OnInit {
  typeAnalyse: ITypeAnalyse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeAnalyse }) => (this.typeAnalyse = typeAnalyse));
  }

  previousState(): void {
    window.history.back();
  }
}
