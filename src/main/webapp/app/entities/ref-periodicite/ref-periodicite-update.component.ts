import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRefPeriodicite, RefPeriodicite } from 'app/shared/model/ref-periodicite.model';
import { RefPeriodiciteService } from './ref-periodicite.service';

@Component({
  selector: 'jhi-ref-periodicite-update',
  templateUrl: './ref-periodicite-update.component.html',
})
export class RefPeriodiciteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected refPeriodiciteService: RefPeriodiciteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refPeriodicite }) => {
      this.updateForm(refPeriodicite);
    });
  }

  updateForm(refPeriodicite: IRefPeriodicite): void {
    this.editForm.patchValue({
      id: refPeriodicite.id,
      libelle: refPeriodicite.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const refPeriodicite = this.createFromForm();
    if (refPeriodicite.id !== undefined) {
      this.subscribeToSaveResponse(this.refPeriodiciteService.update(refPeriodicite));
    } else {
      this.subscribeToSaveResponse(this.refPeriodiciteService.create(refPeriodicite));
    }
  }

  private createFromForm(): IRefPeriodicite {
    return {
      ...new RefPeriodicite(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefPeriodicite>>): void {
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
