import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';
import { ModeEvacExcretaService } from './mode-evac-excreta.service';

@Component({
  templateUrl: './mode-evac-excreta-delete-dialog.component.html',
})
export class ModeEvacExcretaDeleteDialogComponent {
  modeEvacExcreta?: IModeEvacExcreta;

  constructor(
    protected modeEvacExcretaService: ModeEvacExcretaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.modeEvacExcretaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('modeEvacExcretaListModification');
      this.activeModal.close();
    });
  }
}
