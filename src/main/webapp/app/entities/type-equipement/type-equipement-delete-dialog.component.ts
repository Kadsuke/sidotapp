import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeEquipement } from 'app/shared/model/type-equipement.model';
import { TypeEquipementService } from './type-equipement.service';

@Component({
  templateUrl: './type-equipement-delete-dialog.component.html',
})
export class TypeEquipementDeleteDialogComponent {
  typeEquipement?: ITypeEquipement;

  constructor(
    protected typeEquipementService: TypeEquipementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeEquipementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeEquipementListModification');
      this.activeModal.close();
    });
  }
}
