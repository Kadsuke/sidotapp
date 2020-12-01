import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';
import { PrevisionAssainissementColService } from './prevision-assainissement-col.service';

@Component({
  templateUrl: './prevision-assainissement-col-delete-dialog.component.html',
})
export class PrevisionAssainissementColDeleteDialogComponent {
  previsionAssainissementCol?: IPrevisionAssainissementCol;

  constructor(
    protected previsionAssainissementColService: PrevisionAssainissementColService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.previsionAssainissementColService.delete(id).subscribe(() => {
      this.eventManager.broadcast('previsionAssainissementColListModification');
      this.activeModal.close();
    });
  }
}
