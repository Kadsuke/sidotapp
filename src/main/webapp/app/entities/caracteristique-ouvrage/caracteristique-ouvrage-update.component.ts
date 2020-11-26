import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICaracteristiqueOuvrage, CaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';
import { CaracteristiqueOuvrageService } from './caracteristique-ouvrage.service';
import { ITypeOuvrage } from 'app/shared/model/type-ouvrage.model';
import { TypeOuvrageService } from 'app/entities/type-ouvrage/type-ouvrage.service';

@Component({
  selector: 'jhi-caracteristique-ouvrage-update',
  templateUrl: './caracteristique-ouvrage-update.component.html',
})
export class CaracteristiqueOuvrageUpdateComponent implements OnInit {
  isSaving = false;
  typeouvrages: ITypeOuvrage[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    unite: [],
    typeouvrageId: [],
  });

  constructor(
    protected caracteristiqueOuvrageService: CaracteristiqueOuvrageService,
    protected typeOuvrageService: TypeOuvrageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ caracteristiqueOuvrage }) => {
      this.updateForm(caracteristiqueOuvrage);

      this.typeOuvrageService.query().subscribe((res: HttpResponse<ITypeOuvrage[]>) => (this.typeouvrages = res.body || []));
    });
  }

  updateForm(caracteristiqueOuvrage: ICaracteristiqueOuvrage): void {
    this.editForm.patchValue({
      id: caracteristiqueOuvrage.id,
      libelle: caracteristiqueOuvrage.libelle,
      unite: caracteristiqueOuvrage.unite,
      typeouvrageId: caracteristiqueOuvrage.typeouvrageId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const caracteristiqueOuvrage = this.createFromForm();
    if (caracteristiqueOuvrage.id !== undefined) {
      this.subscribeToSaveResponse(this.caracteristiqueOuvrageService.update(caracteristiqueOuvrage));
    } else {
      this.subscribeToSaveResponse(this.caracteristiqueOuvrageService.create(caracteristiqueOuvrage));
    }
  }

  private createFromForm(): ICaracteristiqueOuvrage {
    return {
      ...new CaracteristiqueOuvrage(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      unite: this.editForm.get(['unite'])!.value,
      typeouvrageId: this.editForm.get(['typeouvrageId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaracteristiqueOuvrage>>): void {
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

  trackById(index: number, item: ITypeOuvrage): any {
    return item.id;
  }
}
