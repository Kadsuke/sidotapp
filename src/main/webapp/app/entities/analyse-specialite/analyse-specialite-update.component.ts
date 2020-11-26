import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAnalyseSpecialite, AnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';
import { AnalyseSpecialiteService } from './analyse-specialite.service';

@Component({
  selector: 'jhi-analyse-specialite-update',
  templateUrl: './analyse-specialite-update.component.html',
})
export class AnalyseSpecialiteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(
    protected analyseSpecialiteService: AnalyseSpecialiteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ analyseSpecialite }) => {
      this.updateForm(analyseSpecialite);
    });
  }

  updateForm(analyseSpecialite: IAnalyseSpecialite): void {
    this.editForm.patchValue({
      id: analyseSpecialite.id,
      libelle: analyseSpecialite.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const analyseSpecialite = this.createFromForm();
    if (analyseSpecialite.id !== undefined) {
      this.subscribeToSaveResponse(this.analyseSpecialiteService.update(analyseSpecialite));
    } else {
      this.subscribeToSaveResponse(this.analyseSpecialiteService.create(analyseSpecialite));
    }
  }

  private createFromForm(): IAnalyseSpecialite {
    return {
      ...new AnalyseSpecialite(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnalyseSpecialite>>): void {
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
