import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoTypeCommune } from 'app/shared/model/geo-type-commune.model';

@Component({
  selector: 'jhi-geo-type-commune-detail',
  templateUrl: './geo-type-commune-detail.component.html',
})
export class GeoTypeCommuneDetailComponent implements OnInit {
  geoTypeCommune: IGeoTypeCommune | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoTypeCommune }) => (this.geoTypeCommune = geoTypeCommune));
  }

  previousState(): void {
    window.history.back();
  }
}
