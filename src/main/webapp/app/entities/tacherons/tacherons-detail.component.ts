import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITacherons } from 'app/shared/model/tacherons.model';

@Component({
  selector: 'jhi-tacherons-detail',
  templateUrl: './tacherons-detail.component.html',
})
export class TacheronsDetailComponent implements OnInit {
  tacherons: ITacherons | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tacherons }) => (this.tacherons = tacherons));
  }

  previousState(): void {
    window.history.back();
  }
}
