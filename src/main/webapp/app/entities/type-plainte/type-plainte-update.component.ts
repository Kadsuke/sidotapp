import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITypePlainte, TypePlainte } from 'app/shared/model/type-plainte.model';
import { TypePlainteService } from './type-plainte.service';

@Component({
  selector: 'jhi-type-plainte-update',
  templateUrl: './type-plainte-update.component.html',
})
export class TypePlainteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected typePlainteService: TypePlainteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typePlainte }) => {
      this.updateForm(typePlainte);
    });
  }

  updateForm(typePlainte: ITypePlainte): void {
    this.editForm.patchValue({
      id: typePlainte.id,
      libelle: typePlainte.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typePlainte = this.createFromForm();
    if (typePlainte.id !== undefined) {
      this.subscribeToSaveResponse(this.typePlainteService.update(typePlainte));
    } else {
      this.subscribeToSaveResponse(this.typePlainteService.create(typePlainte));
    }
  }

  private createFromForm(): ITypePlainte {
    return {
      ...new TypePlainte(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypePlainte>>): void {
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
