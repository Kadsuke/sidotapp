import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuUsage } from 'app/shared/model/geu-usage.model';
import { GeuUsageService } from './geu-usage.service';

@Component({
  templateUrl: './geu-usage-delete-dialog.component.html',
})
export class GeuUsageDeleteDialogComponent {
  geuUsage?: IGeuUsage;

  constructor(protected geuUsageService: GeuUsageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuUsageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuUsageListModification');
      this.activeModal.close();
    });
  }
}
