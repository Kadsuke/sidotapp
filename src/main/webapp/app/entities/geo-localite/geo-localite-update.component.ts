import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoLocalite, GeoLocalite } from 'app/shared/model/geo-localite.model';
import { GeoLocaliteService } from './geo-localite.service';
import { IGeoCommune } from 'app/shared/model/geo-commune.model';
import { GeoCommuneService } from 'app/entities/geo-commune/geo-commune.service';

@Component({
  selector: 'jhi-geo-localite-update',
  templateUrl: './geo-localite-update.component.html',
})
export class GeoLocaliteUpdateComponent implements OnInit {
  isSaving = false;
  geocommunes: IGeoCommune[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    geocommuneId: [],
  });

  constructor(
    protected geoLocaliteService: GeoLocaliteService,
    protected geoCommuneService: GeoCommuneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoLocalite }) => {
      this.updateForm(geoLocalite);

      this.geoCommuneService.query().subscribe((res: HttpResponse<IGeoCommune[]>) => (this.geocommunes = res.body || []));
    });
  }

  updateForm(geoLocalite: IGeoLocalite): void {
    this.editForm.patchValue({
      id: geoLocalite.id,
      libelle: geoLocalite.libelle,
      geocommuneId: geoLocalite.geocommuneId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoLocalite = this.createFromForm();
    if (geoLocalite.id !== undefined) {
      this.subscribeToSaveResponse(this.geoLocaliteService.update(geoLocalite));
    } else {
      this.subscribeToSaveResponse(this.geoLocaliteService.create(geoLocalite));
    }
  }

  private createFromForm(): IGeoLocalite {
    return {
      ...new GeoLocalite(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      geocommuneId: this.editForm.get(['geocommuneId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoLocalite>>): void {
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

  trackById(index: number, item: IGeoCommune): any {
    return item.id;
  }
}
