import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefPeriodicite } from 'app/shared/model/ref-periodicite.model';

@Component({
  selector: 'jhi-ref-periodicite-detail',
  templateUrl: './ref-periodicite-detail.component.html',
})
export class RefPeriodiciteDetailComponent implements OnInit {
  refPeriodicite: IRefPeriodicite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refPeriodicite }) => (this.refPeriodicite = refPeriodicite));
  }

  previousState(): void {
    window.history.back();
  }
}
