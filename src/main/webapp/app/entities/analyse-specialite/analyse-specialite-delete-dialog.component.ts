import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';
import { AnalyseSpecialiteService } from './analyse-specialite.service';

@Component({
  templateUrl: './analyse-specialite-delete-dialog.component.html',
})
export class AnalyseSpecialiteDeleteDialogComponent {
  analyseSpecialite?: IAnalyseSpecialite;

  constructor(
    protected analyseSpecialiteService: AnalyseSpecialiteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.analyseSpecialiteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('analyseSpecialiteListModification');
      this.activeModal.close();
    });
  }
}
