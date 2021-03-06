import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICentre, Centre } from 'app/shared/model/centre.model';
import { CentreService } from './centre.service';
import { ICentreRegroupement } from 'app/shared/model/centre-regroupement.model';
import { CentreRegroupementService } from 'app/entities/centre-regroupement/centre-regroupement.service';

@Component({
  selector: 'jhi-centre-update',
  templateUrl: './centre-update.component.html',
})
export class CentreUpdateComponent implements OnInit {
  isSaving = false;
  centreregroupements: ICentreRegroupement[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    responsable: [null, [Validators.required]],
    contact: [null, [Validators.required]],
    centreregroupementId: [],
  });

  constructor(
    protected centreService: CentreService,
    protected centreRegroupementService: CentreRegroupementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centre }) => {
      this.updateForm(centre);

      this.centreRegroupementService
        .query()
        .subscribe((res: HttpResponse<ICentreRegroupement[]>) => (this.centreregroupements = res.body || []));
    });
  }

  updateForm(centre: ICentre): void {
    this.editForm.patchValue({
      id: centre.id,
      libelle: centre.libelle,
      responsable: centre.responsable,
      contact: centre.contact,
      centreregroupementId: centre.centreregroupementId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const centre = this.createFromForm();
    if (centre.id !== undefined) {
      this.subscribeToSaveResponse(this.centreService.update(centre));
    } else {
      this.subscribeToSaveResponse(this.centreService.create(centre));
    }
  }

  private createFromForm(): ICentre {
    return {
      ...new Centre(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      centreregroupementId: this.editForm.get(['centreregroupementId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICentre>>): void {
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

  trackById(index: number, item: ICentreRegroupement): any {
    return item.id;
  }
}
