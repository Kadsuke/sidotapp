import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRefDomaine, RefDomaine } from 'app/shared/model/ref-domaine.model';
import { RefDomaineService } from './ref-domaine.service';

@Component({
  selector: 'jhi-ref-domaine-update',
  templateUrl: './ref-domaine-update.component.html',
})
export class RefDomaineUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected refDomaineService: RefDomaineService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refDomaine }) => {
      this.updateForm(refDomaine);
    });
  }

  updateForm(refDomaine: IRefDomaine): void {
    this.editForm.patchValue({
      id: refDomaine.id,
      libelle: refDomaine.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const refDomaine = this.createFromForm();
    if (refDomaine.id !== undefined) {
      this.subscribeToSaveResponse(this.refDomaineService.update(refDomaine));
    } else {
      this.subscribeToSaveResponse(this.refDomaineService.create(refDomaine));
    }
  }

  private createFromForm(): IRefDomaine {
    return {
      ...new RefDomaine(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefDomaine>>): void {
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
