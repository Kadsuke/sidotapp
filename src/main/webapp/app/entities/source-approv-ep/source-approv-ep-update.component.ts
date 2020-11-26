import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISourceApprovEp, SourceApprovEp } from 'app/shared/model/source-approv-ep.model';
import { SourceApprovEpService } from './source-approv-ep.service';

@Component({
  selector: 'jhi-source-approv-ep-update',
  templateUrl: './source-approv-ep-update.component.html',
})
export class SourceApprovEpUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected sourceApprovEpService: SourceApprovEpService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sourceApprovEp }) => {
      this.updateForm(sourceApprovEp);
    });
  }

  updateForm(sourceApprovEp: ISourceApprovEp): void {
    this.editForm.patchValue({
      id: sourceApprovEp.id,
      libelle: sourceApprovEp.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sourceApprovEp = this.createFromForm();
    if (sourceApprovEp.id !== undefined) {
      this.subscribeToSaveResponse(this.sourceApprovEpService.update(sourceApprovEp));
    } else {
      this.subscribeToSaveResponse(this.sourceApprovEpService.create(sourceApprovEp));
    }
  }

  private createFromForm(): ISourceApprovEp {
    return {
      ...new SourceApprovEp(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISourceApprovEp>>): void {
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
