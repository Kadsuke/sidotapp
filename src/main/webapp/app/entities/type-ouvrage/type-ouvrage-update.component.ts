import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeOuvrage, TypeOuvrage } from 'app/shared/model/type-ouvrage.model';
import { TypeOuvrageService } from './type-ouvrage.service';
import { ICaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';
import { CaracteristiqueOuvrageService } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage.service';
import { ICategorieRessources } from 'app/shared/model/categorie-ressources.model';
import { CategorieRessourcesService } from 'app/entities/categorie-ressources/categorie-ressources.service';

type SelectableEntity = ICategorieRessources | ICaracteristiqueOuvrage;

@Component({
  selector: 'jhi-type-ouvrage-update',
  templateUrl: './type-ouvrage-update.component.html',
})
export class TypeOuvrageUpdateComponent implements OnInit {
  isSaving = false;
  categorieressources: ICategorieRessources[] = [];
  caracteristiqueouvrages: ICaracteristiqueOuvrage[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    categorieressourcesId: [],
    caracteristiqueouvrageId: [],
  });

  constructor(
    protected typeOuvrageService: TypeOuvrageService,
    protected caracteristiqueOuvrageService: CaracteristiqueOuvrageService,
    protected categorieRessourcesService: CategorieRessourcesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeOuvrage }) => {
      this.updateForm(typeOuvrage);

      this.categorieRessourcesService
        .query()
        .subscribe((res: HttpResponse<ICategorieRessources[]>) => (this.categorieressources = res.body || []));

      this.caracteristiqueOuvrageService
        .query()
        .subscribe((res: HttpResponse<ICaracteristiqueOuvrage[]>) => (this.caracteristiqueouvrages = res.body || []));
    });
  }

  updateForm(typeOuvrage: ITypeOuvrage): void {
    this.editForm.patchValue({
      id: typeOuvrage.id,
      libelle: typeOuvrage.libelle,
      categorieressourcesId: typeOuvrage.categorieressourcesId,
      caracteristiqueouvrageId: typeOuvrage.caracteristiqueouvrageId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeOuvrage = this.createFromForm();
    if (typeOuvrage.id !== undefined) {
      this.subscribeToSaveResponse(this.typeOuvrageService.update(typeOuvrage));
    } else {
      this.subscribeToSaveResponse(this.typeOuvrageService.create(typeOuvrage));
    }
  }

  private createFromForm(): ITypeOuvrage {
    return {
      ...new TypeOuvrage(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      categorieressourcesId: this.editForm.get(['categorieressourcesId'])!.value,
      caracteristiqueouvrageId: this.editForm.get(['caracteristiqueouvrageId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeOuvrage>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
