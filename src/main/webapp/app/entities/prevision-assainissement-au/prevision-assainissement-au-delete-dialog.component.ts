import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';
import { PrevisionAssainissementAuService } from './prevision-assainissement-au.service';

@Component({
  templateUrl: './prevision-assainissement-au-delete-dialog.component.html',
})
export class PrevisionAssainissementAuDeleteDialogComponent {
  previsionAssainissementAu?: IPrevisionAssainissementAu;

  constructor(
    protected previsionAssainissementAuService: PrevisionAssainissementAuService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.previsionAssainissementAuService.delete(id).subscribe(() => {
      this.eventManager.broadcast('previsionAssainissementAuListModification');
      this.activeModal.close();
    });
  }
}
