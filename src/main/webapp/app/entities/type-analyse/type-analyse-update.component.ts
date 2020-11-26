import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypeAnalyse, TypeAnalyse } from 'app/shared/model/type-analyse.model';
import { TypeAnalyseService } from './type-analyse.service';

@Component({
  selector: 'jhi-type-analyse-update',
  templateUrl: './type-analyse-update.component.html',
})
export class TypeAnalyseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected typeAnalyseService: TypeAnalyseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeAnalyse }) => {
      this.updateForm(typeAnalyse);
    });
  }

  updateForm(typeAnalyse: ITypeAnalyse): void {
    this.editForm.patchValue({
      id: typeAnalyse.id,
      libelle: typeAnalyse.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typeAnalyse = this.createFromForm();
    if (typeAnalyse.id !== undefined) {
      this.subscribeToSaveResponse(this.typeAnalyseService.update(typeAnalyse));
    } else {
      this.subscribeToSaveResponse(this.typeAnalyseService.create(typeAnalyse));
    }
  }

  private createFromForm(): ITypeAnalyse {
    return {
      ...new TypeAnalyse(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypeAnalyse>>): void {
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
