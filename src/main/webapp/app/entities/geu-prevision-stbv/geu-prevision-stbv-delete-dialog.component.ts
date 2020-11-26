import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';
import { GeuPrevisionSTBVService } from './geu-prevision-stbv.service';

@Component({
  templateUrl: './geu-prevision-stbv-delete-dialog.component.html',
})
export class GeuPrevisionSTBVDeleteDialogComponent {
  geuPrevisionSTBV?: IGeuPrevisionSTBV;

  constructor(
    protected geuPrevisionSTBVService: GeuPrevisionSTBVService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuPrevisionSTBVService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuPrevisionSTBVListModification');
      this.activeModal.close();
    });
  }
}
