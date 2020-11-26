import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeOuvrage } from 'app/shared/model/type-ouvrage.model';
import { TypeOuvrageService } from './type-ouvrage.service';

@Component({
  templateUrl: './type-ouvrage-delete-dialog.component.html',
})
export class TypeOuvrageDeleteDialogComponent {
  typeOuvrage?: ITypeOuvrage;

  constructor(
    protected typeOuvrageService: TypeOuvrageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.typeOuvrageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeOuvrageListModification');
      this.activeModal.close();
    });
  }
}
