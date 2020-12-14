import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICentreRegroupement, CentreRegroupement } from 'app/shared/model/centre-regroupement.model';
import { CentreRegroupementService } from './centre-regroupement.service';
import { IDirectionRegionale } from 'app/shared/model/direction-regionale.model';
import { DirectionRegionaleService } from 'app/entities/direction-regionale/direction-regionale.service';

@Component({
  selector: 'jhi-centre-regroupement-update',
  templateUrl: './centre-regroupement-update.component.html',
})
export class CentreRegroupementUpdateComponent implements OnInit {
  isSaving = false;
  directionregionales: IDirectionRegionale[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    responsable: [null, [Validators.required]],
    contact: [null, [Validators.required]],
    directionregionaleId: [],
  });

  constructor(
    protected centreRegroupementService: CentreRegroupementService,
    protected directionRegionaleService: DirectionRegionaleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centreRegroupement }) => {
      this.updateForm(centreRegroupement);

      this.directionRegionaleService
        .query()
        .subscribe((res: HttpResponse<IDirectionRegionale[]>) => (this.directionregionales = res.body || []));
    });
  }

  updateForm(centreRegroupement: ICentreRegroupement): void {
    this.editForm.patchValue({
      id: centreRegroupement.id,
      libelle: centreRegroupement.libelle,
      responsable: centreRegroupement.responsable,
      contact: centreRegroupement.contact,
      directionregionaleId: centreRegroupement.directionregionaleId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const centreRegroupement = this.createFromForm();
    if (centreRegroupement.id !== undefined) {
      this.subscribeToSaveResponse(this.centreRegroupementService.update(centreRegroupement));
    } else {
      this.subscribeToSaveResponse(this.centreRegroupementService.create(centreRegroupement));
    }
  }

  private createFromForm(): ICentreRegroupement {
    return {
      ...new CentreRegroupement(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      directionregionaleId: this.editForm.get(['directionregionaleId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICentreRegroupement>>): void {
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

  trackById(index: number, item: IDirectionRegionale): any {
    return item.id;
  }
}
