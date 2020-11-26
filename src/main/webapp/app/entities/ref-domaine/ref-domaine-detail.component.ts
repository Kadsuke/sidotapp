import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefDomaine } from 'app/shared/model/ref-domaine.model';

@Component({
  selector: 'jhi-ref-domaine-detail',
  templateUrl: './ref-domaine-detail.component.html',
})
export class RefDomaineDetailComponent implements OnInit {
  refDomaine: IRefDomaine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refDomaine }) => (this.refDomaine = refDomaine));
  }

  previousState(): void {
    window.history.back();
  }
}
