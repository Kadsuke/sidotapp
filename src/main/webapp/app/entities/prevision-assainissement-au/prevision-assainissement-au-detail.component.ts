import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';

@Component({
  selector: 'jhi-prevision-assainissement-au-detail',
  templateUrl: './prevision-assainissement-au-detail.component.html',
})
export class PrevisionAssainissementAuDetailComponent implements OnInit {
  previsionAssainissementAu: IPrevisionAssainissementAu | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsionAssainissementAu }) => (this.previsionAssainissementAu = previsionAssainissementAu));
  }

  previousState(): void {
    window.history.back();
  }
}
