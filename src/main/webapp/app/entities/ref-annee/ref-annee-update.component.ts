import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRefAnnee, RefAnnee } from 'app/shared/model/ref-annee.model';
import { RefAnneeService } from './ref-annee.service';

@Component({
  selector: 'jhi-ref-annee-update',
  templateUrl: './ref-annee-update.component.html',
})
export class RefAnneeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected refAnneeService: RefAnneeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refAnnee }) => {
      this.updateForm(refAnnee);
    });
  }

  updateForm(refAnnee: IRefAnnee): void {
    this.editForm.patchValue({
      id: refAnnee.id,
      libelle: refAnnee.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const refAnnee = this.createFromForm();
    if (refAnnee.id !== undefined) {
      this.subscribeToSaveResponse(this.refAnneeService.update(refAnnee));
    } else {
      this.subscribeToSaveResponse(this.refAnneeService.create(refAnnee));
    }
  }

  private createFromForm(): IRefAnnee {
    return {
      ...new RefAnnee(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefAnnee>>): void {
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
