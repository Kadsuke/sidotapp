import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoSection } from 'app/shared/model/geo-section.model';

@Component({
  selector: 'jhi-geo-section-detail',
  templateUrl: './geo-section-detail.component.html',
})
export class GeoSectionDetailComponent implements OnInit {
  geoSection: IGeoSection | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoSection }) => (this.geoSection = geoSection));
  }

  previousState(): void {
    window.history.back();
  }
}
