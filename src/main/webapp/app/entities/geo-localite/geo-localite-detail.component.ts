import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoLocalite } from 'app/shared/model/geo-localite.model';

@Component({
  selector: 'jhi-geo-localite-detail',
  templateUrl: './geo-localite-detail.component.html',
})
export class GeoLocaliteDetailComponent implements OnInit {
  geoLocalite: IGeoLocalite | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoLocalite }) => (this.geoLocalite = geoLocalite));
  }

  previousState(): void {
    window.history.back();
  }
}
