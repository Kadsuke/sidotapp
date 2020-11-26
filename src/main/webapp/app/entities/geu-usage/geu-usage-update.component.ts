import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuUsage, GeuUsage } from 'app/shared/model/geu-usage.model';
import { GeuUsageService } from './geu-usage.service';

@Component({
  selector: 'jhi-geu-usage-update',
  templateUrl: './geu-usage-update.component.html',
})
export class GeuUsageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected geuUsageService: GeuUsageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuUsage }) => {
      this.updateForm(geuUsage);
    });
  }

  updateForm(geuUsage: IGeuUsage): void {
    this.editForm.patchValue({
      id: geuUsage.id,
      libelle: geuUsage.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuUsage = this.createFromForm();
    if (geuUsage.id !== undefined) {
      this.subscribeToSaveResponse(this.geuUsageService.update(geuUsage));
    } else {
      this.subscribeToSaveResponse(this.geuUsageService.create(geuUsage));
    }
  }

  private createFromForm(): IGeuUsage {
    return {
      ...new GeuUsage(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuUsage>>): void {
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
