import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtatEquipement } from 'app/shared/model/etat-equipement.model';

@Component({
  selector: 'jhi-etat-equipement-detail',
  templateUrl: './etat-equipement-detail.component.html',
})
export class EtatEquipementDetailComponent implements OnInit {
  etatEquipement: IEtatEquipement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatEquipement }) => (this.etatEquipement = etatEquipement));
  }

  previousState(): void {
    window.history.back();
  }
}
