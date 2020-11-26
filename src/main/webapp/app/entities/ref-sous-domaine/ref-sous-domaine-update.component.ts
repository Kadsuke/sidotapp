import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRefSousDomaine, RefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';
import { RefSousDomaineService } from './ref-sous-domaine.service';

@Component({
  selector: 'jhi-ref-sous-domaine-update',
  templateUrl: './ref-sous-domaine-update.component.html',
})
export class RefSousDomaineUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected refSousDomaineService: RefSousDomaineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refSousDomaine }) => {
      this.updateForm(refSousDomaine);
    });
  }

  updateForm(refSousDomaine: IRefSousDomaine): void {
    this.editForm.patchValue({
      id: refSousDomaine.id,
      libelle: refSousDomaine.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const refSousDomaine = this.createFromForm();
    if (refSousDomaine.id !== undefined) {
      this.subscribeToSaveResponse(this.refSousDomaineService.update(refSousDomaine));
    } else {
      this.subscribeToSaveResponse(this.refSousDomaineService.create(refSousDomaine));
    }
  }

  private createFromForm(): IRefSousDomaine {
    return {
      ...new RefSousDomaine(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefSousDomaine>>): void {
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
