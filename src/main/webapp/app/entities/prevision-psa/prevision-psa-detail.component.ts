import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrevisionPsa } from 'app/shared/model/prevision-psa.model';

@Component({
  selector: 'jhi-prevision-psa-detail',
  templateUrl: './prevision-psa-detail.component.html',
})
export class PrevisionPsaDetailComponent implements OnInit {
  previsionPsa: IPrevisionPsa | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsionPsa }) => (this.previsionPsa = previsionPsa));
  }

  previousState(): void {
    window.history.back();
  }
}
