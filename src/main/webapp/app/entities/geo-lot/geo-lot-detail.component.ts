import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoLot } from 'app/shared/model/geo-lot.model';

@Component({
  selector: 'jhi-geo-lot-detail',
  templateUrl: './geo-lot-detail.component.html',
})
export class GeoLotDetailComponent implements OnInit {
  geoLot: IGeoLot | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoLot }) => (this.geoLot = geoLot));
  }

  previousState(): void {
    window.history.back();
  }
}
