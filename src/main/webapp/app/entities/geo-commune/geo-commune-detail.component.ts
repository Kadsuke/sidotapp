import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoCommune } from 'app/shared/model/geo-commune.model';

@Component({
  selector: 'jhi-geo-commune-detail',
  templateUrl: './geo-commune-detail.component.html',
})
export class GeoCommuneDetailComponent implements OnInit {
  geoCommune: IGeoCommune | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoCommune }) => (this.geoCommune = geoCommune));
  }

  previousState(): void {
    window.history.back();
  }
}
