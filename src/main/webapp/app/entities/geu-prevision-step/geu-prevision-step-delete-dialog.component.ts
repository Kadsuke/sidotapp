import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';
import { GeuPrevisionSTEPService } from './geu-prevision-step.service';

@Component({
  templateUrl: './geu-prevision-step-delete-dialog.component.html',
})
export class GeuPrevisionSTEPDeleteDialogComponent {
  geuPrevisionSTEP?: IGeuPrevisionSTEP;

  constructor(
    protected geuPrevisionSTEPService: GeuPrevisionSTEPService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuPrevisionSTEPService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuPrevisionSTEPListModification');
      this.activeModal.close();
    });
  }
}
