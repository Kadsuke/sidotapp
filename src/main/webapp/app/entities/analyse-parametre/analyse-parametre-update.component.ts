import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAnalyseParametre, AnalyseParametre } from 'app/shared/model/analyse-parametre.model';
import { AnalyseParametreService } from './analyse-parametre.service';
import { IAnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';
import { AnalyseSpecialiteService } from 'app/entities/analyse-specialite/analyse-specialite.service';

@Component({
  selector: 'jhi-analyse-parametre-update',
  templateUrl: './analyse-parametre-update.component.html',
})
export class AnalyseParametreUpdateComponent implements OnInit {
  isSaving = false;
  analysespecialites: IAnalyseSpecialite[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    analysespecialiteId: [],
  });

  constructor(
    protected analyseParametreService: AnalyseParametreService,
    protected analyseSpecialiteService: AnalyseSpecialiteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ analyseParametre }) => {
      this.updateForm(analyseParametre);

      this.analyseSpecialiteService
        .query()
        .subscribe((res: HttpResponse<IAnalyseSpecialite[]>) => (this.analysespecialites = res.body || []));
    });
  }

  updateForm(analyseParametre: IAnalyseParametre): void {
    this.editForm.patchValue({
      id: analyseParametre.id,
      libelle: analyseParametre.libelle,
      analysespecialiteId: analyseParametre.analysespecialiteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const analyseParametre = this.createFromForm();
    if (analyseParametre.id !== undefined) {
      this.subscribeToSaveResponse(this.analyseParametreService.update(analyseParametre));
    } else {
      this.subscribeToSaveResponse(this.analyseParametreService.create(analyseParametre));
    }
  }

  private createFromForm(): IAnalyseParametre {
    return {
      ...new AnalyseParametre(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      analysespecialiteId: this.editForm.get(['analysespecialiteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnalyseParametre>>): void {
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

  trackById(index: number, item: IAnalyseSpecialite): any {
    return item.id;
  }
}
