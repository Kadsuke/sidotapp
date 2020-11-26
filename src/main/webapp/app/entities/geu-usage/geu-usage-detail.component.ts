import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeuUsage } from 'app/shared/model/geu-usage.model';

@Component({
  selector: 'jhi-geu-usage-detail',
  templateUrl: './geu-usage-detail.component.html',
})
export class GeuUsageDetailComponent implements OnInit {
  geuUsage: IGeuUsage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuUsage }) => (this.geuUsage = geuUsage));
  }

  previousState(): void {
    window.history.back();
  }
}
