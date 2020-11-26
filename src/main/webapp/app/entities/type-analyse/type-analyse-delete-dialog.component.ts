import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeAnalyse } from 'app/shared/model/type-analyse.model';
import { TypeAnalyseService } from './type-analyse.service';

@Component({
  templateUrl: './type-analyse-delete-dialog.component.html',
})
export class TypeAnalyseDeleteDialogComponent {
  typeAnalyse?: ITypeAnalyse;

  constructor(
    protected typeAnalyseService: TypeAnalyseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeAnalyseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeAnalyseListModification');
      this.activeModal.close();
    });
  }
}
