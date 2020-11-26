import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypePlainte } from 'app/shared/model/type-plainte.model';

@Component({
  selector: 'jhi-type-plainte-detail',
  templateUrl: './type-plainte-detail.component.html',
})
export class TypePlainteDetailComponent implements OnInit {
  typePlainte: ITypePlainte | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typePlainte }) => (this.typePlainte = typePlainte));
  }

  previousState(): void {
    window.history.back();
  }
}
