import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuRealisation } from 'app/shared/model/geu-realisation.model';
import { GeuRealisationService } from './geu-realisation.service';

@Component({
  templateUrl: './geu-realisation-delete-dialog.component.html',
})
export class GeuRealisationDeleteDialogComponent {
  geuRealisation?: IGeuRealisation;

  constructor(
    protected geuRealisationService: GeuRealisationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuRealisationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuRealisationListModification');
      this.activeModal.close();
    });
  }
}
