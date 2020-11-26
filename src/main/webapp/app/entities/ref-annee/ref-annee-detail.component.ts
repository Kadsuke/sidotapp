import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefAnnee } from 'app/shared/model/ref-annee.model';

@Component({
  selector: 'jhi-ref-annee-detail',
  templateUrl: './ref-annee-detail.component.html',
})
export class RefAnneeDetailComponent implements OnInit {
  refAnnee: IRefAnnee | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refAnnee }) => (this.refAnnee = refAnnee));
  }

  previousState(): void {
    window.history.back();
  }
}
