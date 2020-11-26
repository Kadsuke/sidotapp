import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieRessources } from 'app/shared/model/categorie-ressources.model';
import { CategorieRessourcesService } from './categorie-ressources.service';

@Component({
  templateUrl: './categorie-ressources-delete-dialog.component.html',
})
export class CategorieRessourcesDeleteDialogComponent {
  categorieRessources?: ICategorieRessources;

  constructor(
    protected categorieRessourcesService: CategorieRessourcesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.categorieRessourcesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('categorieRessourcesListModification');
      this.activeModal.close();
    });
  }
}
