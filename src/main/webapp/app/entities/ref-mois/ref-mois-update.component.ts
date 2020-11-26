import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRefMois, RefMois } from 'app/shared/model/ref-mois.model';
import { RefMoisService } from './ref-mois.service';

@Component({
  selector: 'jhi-ref-mois-update',
  templateUrl: './ref-mois-update.component.html',
})
export class RefMoisUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected refMoisService: RefMoisService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refMois }) => {
      this.updateForm(refMois);
    });
  }

  updateForm(refMois: IRefMois): void {
    this.editForm.patchValue({
      id: refMois.id,
      libelle: refMois.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const refMois = this.createFromForm();
    if (refMois.id !== undefined) {
      this.subscribeToSaveResponse(this.refMoisService.update(refMois));
    } else {
      this.subscribeToSaveResponse(this.refMoisService.create(refMois));
    }
  }

  private createFromForm(): IRefMois {
    return {
      ...new RefMois(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefMois>>): void {
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
