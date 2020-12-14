import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEtatProgram, EtatProgram } from 'app/shared/model/etat-program.model';
import { EtatProgramService } from './etat-program.service';

@Component({
  selector: 'jhi-etat-program-update',
  templateUrl: './etat-program-update.component.html',
})
export class EtatProgramUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected etatProgramService: EtatProgramService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatProgram }) => {
      this.updateForm(etatProgram);
    });
  }

  updateForm(etatProgram: IEtatProgram): void {
    this.editForm.patchValue({
      id: etatProgram.id,
      libelle: etatProgram.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const etatProgram = this.createFromForm();
    if (etatProgram.id !== undefined) {
      this.subscribeToSaveResponse(this.etatProgramService.update(etatProgram));
    } else {
      this.subscribeToSaveResponse(this.etatProgramService.create(etatProgram));
    }
  }

  private createFromForm(): IEtatProgram {
    return {
      ...new EtatProgram(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtatProgram>>): void {
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
