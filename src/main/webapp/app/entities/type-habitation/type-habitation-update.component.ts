import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeHabitation, TypeHabitation } from 'app/shared/model/type-habitation.model';
import { TypeHabitationService } from './type-habitation.service';

@Component({
  selector: 'jhi-type-habitation-update',
  templateUrl: './type-habitation-update.component.html',
})
export class TypeHabitationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected typeHabitationService: TypeHabitationService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeHabitation }) => {
      this.updateForm(typeHabitation);
    });
  }

  updateForm(typeHabitation: ITypeHabitation): void {
    this.editForm.patchValue({
      id: typeHabitation.id,
      libelle: typeHabitation.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeHabitation = this.createFromForm();
    if (typeHabitation.id !== undefined) {
      this.subscribeToSaveResponse(this.typeHabitationService.update(typeHabitation));
    } else {
      this.subscribeToSaveResponse(this.typeHabitationService.create(typeHabitation));
    }
  }

  private createFromForm(): ITypeHabitation {
    return {
      ...new TypeHabitation(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeHabitation>>): void {
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
