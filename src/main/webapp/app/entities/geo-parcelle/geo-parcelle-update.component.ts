import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoParcelle, GeoParcelle } from 'app/shared/model/geo-parcelle.model';
import { GeoParcelleService } from './geo-parcelle.service';
import { IGeoLot } from 'app/shared/model/geo-lot.model';
import { GeoLotService } from 'app/entities/geo-lot/geo-lot.service';

@Component({
  selector: 'jhi-geo-parcelle-update',
  templateUrl: './geo-parcelle-update.component.html',
})
export class GeoParcelleUpdateComponent implements OnInit {
  isSaving = false;
  geolots: IGeoLot[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    geolotId: [],
  });

  constructor(
    protected geoParcelleService: GeoParcelleService,
    protected geoLotService: GeoLotService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoParcelle }) => {
      this.updateForm(geoParcelle);

      this.geoLotService.query().subscribe((res: HttpResponse<IGeoLot[]>) => (this.geolots = res.body || []));
    });
  }

  updateForm(geoParcelle: IGeoParcelle): void {
    this.editForm.patchValue({
      id: geoParcelle.id,
      libelle: geoParcelle.libelle,
      geolotId: geoParcelle.geolotId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoParcelle = this.createFromForm();
    if (geoParcelle.id !== undefined) {
      this.subscribeToSaveResponse(this.geoParcelleService.update(geoParcelle));
    } else {
      this.subscribeToSaveResponse(this.geoParcelleService.create(geoParcelle));
    }
  }

  private createFromForm(): IGeoParcelle {
    return {
      ...new GeoParcelle(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      geolotId: this.editForm.get(['geolotId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoParcelle>>): void {
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

  trackById(index: number, item: IGeoLot): any {
    return item.id;
  }
}
