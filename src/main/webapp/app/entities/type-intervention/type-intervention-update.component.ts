import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeIntervention, TypeIntervention } from 'app/shared/model/type-intervention.model';
import { TypeInterventionService } from './type-intervention.service';

@Component({
  selector: 'jhi-type-intervention-update',
  templateUrl: './type-intervention-update.component.html',
})
export class TypeInterventionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(
    protected typeInterventionService: TypeInterventionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeIntervention }) => {
      this.updateForm(typeIntervention);
    });
  }

  updateForm(typeIntervention: ITypeIntervention): void {
    this.editForm.patchValue({
      id: typeIntervention.id,
      libelle: typeIntervention.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeIntervention = this.createFromForm();
    if (typeIntervention.id !== undefined) {
      this.subscribeToSaveResponse(this.typeInterventionService.update(typeIntervention));
    } else {
      this.subscribeToSaveResponse(this.typeInterventionService.create(typeIntervention));
    }
  }

  private createFromForm(): ITypeIntervention {
    return {
      ...new TypeIntervention(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeIntervention>>): void {
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
