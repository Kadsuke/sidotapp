import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoRegion } from 'app/shared/model/geo-region.model';

@Component({
  selector: 'jhi-geo-region-detail',
  templateUrl: './geo-region-detail.component.html',
})
export class GeoRegionDetailComponent implements OnInit {
  geoRegion: IGeoRegion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoRegion }) => (this.geoRegion = geoRegion));
  }

  previousState(): void {
    window.history.back();
  }
}
