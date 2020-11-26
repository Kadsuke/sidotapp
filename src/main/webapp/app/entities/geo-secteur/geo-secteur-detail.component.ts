import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeoSecteur } from 'app/shared/model/geo-secteur.model';

@Component({
  selector: 'jhi-geo-secteur-detail',
  templateUrl: './geo-secteur-detail.component.html',
})
export class GeoSecteurDetailComponent implements OnInit {
  geoSecteur: IGeoSecteur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoSecteur }) => (this.geoSecteur = geoSecteur));
  }

  previousState(): void {
    window.history.back();
  }
}
