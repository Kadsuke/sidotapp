import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

@Component({
  selector: 'jhi-caracteristique-ouvrage-detail',
  templateUrl: './caracteristique-ouvrage-detail.component.html',
})
export class CaracteristiqueOuvrageDetailComponent implements OnInit {
  caracteristiqueOuvrage: ICaracteristiqueOuvrage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caracteristiqueOuvrage }) => (this.caracteristiqueOuvrage = caracteristiqueOuvrage));
  }

  previousState(): void {
    window.history.back();
  }
}
