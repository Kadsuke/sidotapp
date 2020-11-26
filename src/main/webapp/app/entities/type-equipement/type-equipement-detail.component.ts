import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeEquipement } from 'app/shared/model/type-equipement.model';

@Component({
  selector: 'jhi-type-equipement-detail',
  templateUrl: './type-equipement-detail.component.html',
})
export class TypeEquipementDetailComponent implements OnInit {
  typeEquipement: ITypeEquipement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeEquipement }) => (this.typeEquipement = typeEquipement));
  }

  previousState(): void {
    window.history.back();
  }
}
