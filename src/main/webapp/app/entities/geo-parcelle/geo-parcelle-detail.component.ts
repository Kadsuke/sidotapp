import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoParcelle } from 'app/shared/model/geo-parcelle.model';

@Component({
  selector: 'jhi-geo-parcelle-detail',
  templateUrl: './geo-parcelle-detail.component.html',
})
export class GeoParcelleDetailComponent implements OnInit {
  geoParcelle: IGeoParcelle | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoParcelle }) => (this.geoParcelle = geoParcelle));
  }

  previousState(): void {
    window.history.back();
  }
}
