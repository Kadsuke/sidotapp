import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefMois } from 'app/shared/model/ref-mois.model';

@Component({
  selector: 'jhi-ref-mois-detail',
  templateUrl: './ref-mois-detail.component.html',
})
export class RefMoisDetailComponent implements OnInit {
  refMois: IRefMois | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refMois }) => (this.refMois = refMois));
  }

  previousState(): void {
    window.history.back();
  }
}
