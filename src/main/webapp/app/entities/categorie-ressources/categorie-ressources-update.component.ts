import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategorieRessources, CategorieRessources } from 'app/shared/model/categorie-ressources.model';
import { CategorieRessourcesService } from './categorie-ressources.service';

@Component({
  selector: 'jhi-categorie-ressources-update',
  templateUrl: './categorie-ressources-update.component.html',
})
export class CategorieRessourcesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(
    protected categorieRessourcesService: CategorieRessourcesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieRessources }) => {
      this.updateForm(categorieRessources);
    });
  }

  updateForm(categorieRessources: ICategorieRessources): void {
    this.editForm.patchValue({
      id: categorieRessources.id,
      libelle: categorieRessources.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const categorieRessources = this.createFromForm();
    if (categorieRessources.id !== undefined) {
      this.subscribeToSaveResponse(this.categorieRessourcesService.update(categorieRessources));
    } else {
      this.subscribeToSaveResponse(this.categorieRessourcesService.create(categorieRessources));
    }
  }

  private createFromForm(): ICategorieRessources {
    return {
      ...new CategorieRessources(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieRessources>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
