import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';

@Component({
  selector: 'jhi-ref-sous-domaine-detail',
  templateUrl: './ref-sous-domaine-detail.component.html',
})
export class RefSousDomaineDetailComponent implements OnInit {
  refSousDomaine: IRefSousDomaine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refSousDomaine }) => (this.refSousDomaine = refSousDomaine));
  }

  previousState(): void {
    window.history.back();
  }
}
