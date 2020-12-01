import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';

@Component({
  selector: 'jhi-prevision-assainissement-col-detail',
  templateUrl: './prevision-assainissement-col-detail.component.html',
})
export class PrevisionAssainissementColDetailComponent implements OnInit {
  previsionAssainissementCol: IPrevisionAssainissementCol | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsionAssainissementCol }) => (this.previsionAssainissementCol = previsionAssainissementCol));
  }

  previousState(): void {
    window.history.back();
  }
}
