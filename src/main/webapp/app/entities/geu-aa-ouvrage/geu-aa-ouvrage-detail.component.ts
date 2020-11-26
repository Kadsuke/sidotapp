import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

@Component({
  selector: 'jhi-geu-aa-ouvrage-detail',
  templateUrl: './geu-aa-ouvrage-detail.component.html',
})
export class GeuAaOuvrageDetailComponent implements OnInit {
  geuAaOuvrage: IGeuAaOuvrage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuAaOuvrage }) => (this.geuAaOuvrage = geuAaOuvrage));
  }

  previousState(): void {
    window.history.back();
  }
}
