import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeHabitation } from 'app/shared/model/type-habitation.model';
import { TypeHabitationService } from './type-habitation.service';

@Component({
  templateUrl: './type-habitation-delete-dialog.component.html',
})
export class TypeHabitationDeleteDialogComponent {
  typeHabitation?: ITypeHabitation;

  constructor(
    protected typeHabitationService: TypeHabitationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeHabitationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeHabitationListModification');
      this.activeModal.close();
    });
  }
}
