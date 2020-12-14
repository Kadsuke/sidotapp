import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeBeneficiaire, TypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';
import { TypeBeneficiaireService } from './type-beneficiaire.service';

@Component({
  selector: 'jhi-type-beneficiaire-update',
  templateUrl: './type-beneficiaire-update.component.html',
})
export class TypeBeneficiaireUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(
    protected typeBeneficiaireService: TypeBeneficiaireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeBeneficiaire }) => {
      this.updateForm(typeBeneficiaire);
    });
  }

  updateForm(typeBeneficiaire: ITypeBeneficiaire): void {
    this.editForm.patchValue({
      id: typeBeneficiaire.id,
      libelle: typeBeneficiaire.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeBeneficiaire = this.createFromForm();
    if (typeBeneficiaire.id !== undefined) {
      this.subscribeToSaveResponse(this.typeBeneficiaireService.update(typeBeneficiaire));
    } else {
      this.subscribeToSaveResponse(this.typeBeneficiaireService.create(typeBeneficiaire));
    }
  }

  private createFromForm(): ITypeBeneficiaire {
    return {
      ...new TypeBeneficiaire(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeBeneficiaire>>): void {
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
