import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDirectionRegionale } from 'app/shared/model/direction-regionale.model';
import { DirectionRegionaleService } from './direction-regionale.service';

@Component({
  templateUrl: './direction-regionale-delete-dialog.component.html',
})
export class DirectionRegionaleDeleteDialogComponent {
  directionRegionale?: IDirectionRegionale;

  constructor(
    protected directionRegionaleService: DirectionRegionaleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.directionRegionaleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('directionRegionaleListModification');
      this.activeModal.close();
    });
  }
}
