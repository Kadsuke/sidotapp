import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoProvince } from 'app/shared/model/geo-province.model';

@Component({
  selector: 'jhi-geo-province-detail',
  templateUrl: './geo-province-detail.component.html',
})
export class GeoProvinceDetailComponent implements OnInit {
  geoProvince: IGeoProvince | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoProvince }) => (this.geoProvince = geoProvince));
  }

  previousState(): void {
    window.history.back();
  }
}
