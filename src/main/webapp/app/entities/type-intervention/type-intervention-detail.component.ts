import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeIntervention } from 'app/shared/model/type-intervention.model';

@Component({
  selector: 'jhi-type-intervention-detail',
  templateUrl: './type-intervention-detail.component.html',
})
export class TypeInterventionDetailComponent implements OnInit {
  typeIntervention: ITypeIntervention | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeIntervention }) => (this.typeIntervention = typeIntervention));
  }

  previousState(): void {
    window.history.back();
  }
}
