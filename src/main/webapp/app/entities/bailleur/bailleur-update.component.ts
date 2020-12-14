import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBailleur, Bailleur } from 'app/shared/model/bailleur.model';
import { BailleurService } from './bailleur.service';

@Component({
  selector: 'jhi-bailleur-update',
  templateUrl: './bailleur-update.component.html',
})
export class BailleurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    responsbale: [null, [Validators.required]],
    contact: [null, [Validators.required]],
  });

  constructor(protected bailleurService: BailleurService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bailleur }) => {
      this.updateForm(bailleur);
    });
  }

  updateForm(bailleur: IBailleur): void {
    this.editForm.patchValue({
      id: bailleur.id,
      libelle: bailleur.libelle,
      responsbale: bailleur.responsbale,
      contact: bailleur.contact,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bailleur = this.createFromForm();
    if (bailleur.id !== undefined) {
      this.subscribeToSaveResponse(this.bailleurService.update(bailleur));
    } else {
      this.subscribeToSaveResponse(this.bailleurService.create(bailleur));
    }
  }

  private createFromForm(): IBailleur {
    return {
      ...new Bailleur(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      responsbale: this.editForm.get(['responsbale'])!.value,
      contact: this.editForm.get(['contact'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBailleur>>): void {
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
