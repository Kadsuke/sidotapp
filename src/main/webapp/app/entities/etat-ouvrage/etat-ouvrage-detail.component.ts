import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtatOuvrage } from 'app/shared/model/etat-ouvrage.model';

@Component({
  selector: 'jhi-etat-ouvrage-detail',
  templateUrl: './etat-ouvrage-detail.component.html',
})
export class EtatOuvrageDetailComponent implements OnInit {
  etatOuvrage: IEtatOuvrage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatOuvrage }) => (this.etatOuvrage = etatOuvrage));
  }

  previousState(): void {
    window.history.back();
  }
}
