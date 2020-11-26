import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypePlainte } from 'app/shared/model/type-plainte.model';
import { TypePlainteService } from './type-plainte.service';

@Component({
  templateUrl: './type-plainte-delete-dialog.component.html',
})
export class TypePlainteDeleteDialogComponent {
  typePlainte?: ITypePlainte;

  constructor(
    protected typePlainteService: TypePlainteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typePlainteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typePlainteListModification');
      this.activeModal.close();
    });
  }
}
