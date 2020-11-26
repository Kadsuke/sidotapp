import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeIntervention } from 'app/shared/model/type-intervention.model';
import { TypeInterventionService } from './type-intervention.service';

@Component({
  templateUrl: './type-intervention-delete-dialog.component.html',
})
export class TypeInterventionDeleteDialogComponent {
  typeIntervention?: ITypeIntervention;

  constructor(
    protected typeInterventionService: TypeInterventionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeInterventionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeInterventionListModification');
      this.activeModal.close();
    });
  }
}
