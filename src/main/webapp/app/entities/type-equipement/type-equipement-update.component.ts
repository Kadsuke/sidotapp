import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeEquipement, TypeEquipement } from 'app/shared/model/type-equipement.model';
import { TypeEquipementService } from './type-equipement.service';

@Component({
  selector: 'jhi-type-equipement-update',
  templateUrl: './type-equipement-update.component.html',
})
export class TypeEquipementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected typeEquipementService: TypeEquipementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeEquipement }) => {
      this.updateForm(typeEquipement);
    });
  }

  updateForm(typeEquipement: ITypeEquipement): void {
    this.editForm.patchValue({
      id: typeEquipement.id,
      libelle: typeEquipement.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeEquipement = this.createFromForm();
    if (typeEquipement.id !== undefined) {
      this.subscribeToSaveResponse(this.typeEquipementService.update(typeEquipement));
    } else {
      this.subscribeToSaveResponse(this.typeEquipementService.create(typeEquipement));
    }
  }

  private createFromForm(): ITypeEquipement {
    return {
      ...new TypeEquipement(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeEquipement>>): void {
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
