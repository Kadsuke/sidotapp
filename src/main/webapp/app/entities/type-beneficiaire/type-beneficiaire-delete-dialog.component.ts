import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';
import { TypeBeneficiaireService } from './type-beneficiaire.service';

@Component({
  templateUrl: './type-beneficiaire-delete-dialog.component.html',
})
export class TypeBeneficiaireDeleteDialogComponent {
  typeBeneficiaire?: ITypeBeneficiaire;

  constructor(
    protected typeBeneficiaireService: TypeBeneficiaireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeBeneficiaireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeBeneficiaireListModification');
      this.activeModal.close();
    });
  }
}
