import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';

@Component({
  selector: 'jhi-geu-raccordement-detail',
  templateUrl: './geu-raccordement-detail.component.html',
})
export class GeuRaccordementDetailComponent implements OnInit {
  geuRaccordement: IGeuRaccordement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuRaccordement }) => (this.geuRaccordement = geuRaccordement));
  }

  previousState(): void {
    window.history.back();
  }
}
