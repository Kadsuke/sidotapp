import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISourceApprovEp } from 'app/shared/model/source-approv-ep.model';
import { SourceApprovEpService } from './source-approv-ep.service';

@Component({
  templateUrl: './source-approv-ep-delete-dialog.component.html',
})
export class SourceApprovEpDeleteDialogComponent {
  sourceApprovEp?: ISourceApprovEp;

  constructor(
    protected sourceApprovEpService: SourceApprovEpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sourceApprovEpService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sourceApprovEpListModification');
      this.activeModal.close();
    });
  }
}
