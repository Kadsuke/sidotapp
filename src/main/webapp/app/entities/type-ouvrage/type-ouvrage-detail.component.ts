import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeOuvrage } from 'app/shared/model/type-ouvrage.model';

@Component({
  selector: 'jhi-type-ouvrage-detail',
  templateUrl: './type-ouvrage-detail.component.html',
})
export class TypeOuvrageDetailComponent implements OnInit {
  typeOuvrage: ITypeOuvrage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeOuvrage }) => (this.typeOuvrage = typeOuvrage));
  }

  previousState(): void {
    window.history.back();
  }
}
