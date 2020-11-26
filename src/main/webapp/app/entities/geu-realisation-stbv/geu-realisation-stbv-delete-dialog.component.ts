import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';
import { GeuRealisationSTBVService } from './geu-realisation-stbv.service';

@Component({
  templateUrl: './geu-realisation-stbv-delete-dialog.component.html',
})
export class GeuRealisationSTBVDeleteDialogComponent {
  geuRealisationSTBV?: IGeuRealisationSTBV;

  constructor(
    protected geuRealisationSTBVService: GeuRealisationSTBVService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuRealisationSTBVService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuRealisationSTBVListModification');
      this.activeModal.close();
    });
  }
}
