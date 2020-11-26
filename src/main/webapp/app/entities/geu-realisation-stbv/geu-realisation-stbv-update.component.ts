import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuRealisationSTBV, GeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';
import { GeuRealisationSTBVService } from './geu-realisation-stbv.service';
import { IGeuSTBV } from 'app/shared/model/geu-stbv.model';
import { GeuSTBVService } from 'app/entities/geu-stbv/geu-stbv.service';

@Component({
  selector: 'jhi-geu-realisation-stbv-update',
  templateUrl: './geu-realisation-stbv-update.component.html',
})
export class GeuRealisationSTBVUpdateComponent implements OnInit {
  isSaving = false;
  geustbvs: IGeuSTBV[] = [];

  editForm = this.fb.group({
    id: [],
    nbCamions: [],
    volume: [],
    energie: [],
    geustbvId: [],
  });

  constructor(
    protected geuRealisationSTBVService: GeuRealisationSTBVService,
    protected geuSTBVService: GeuSTBVService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuRealisationSTBV }) => {
      this.updateForm(geuRealisationSTBV);

      this.geuSTBVService.query().subscribe((res: HttpResponse<IGeuSTBV[]>) => (this.geustbvs = res.body || []));
    });
  }

  updateForm(geuRealisationSTBV: IGeuRealisationSTBV): void {
    this.editForm.patchValue({
      id: geuRealisationSTBV.id,
      nbCamions: geuRealisationSTBV.nbCamions,
      volume: geuRealisationSTBV.volume,
      energie: geuRealisationSTBV.energie,
      geustbvId: geuRealisationSTBV.geustbvId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuRealisationSTBV = this.createFromForm();
    if (geuRealisationSTBV.id !== undefined) {
      this.subscribeToSaveResponse(this.geuRealisationSTBVService.update(geuRealisationSTBV));
    } else {
      this.subscribeToSaveResponse(this.geuRealisationSTBVService.create(geuRealisationSTBV));
    }
  }

  private createFromForm(): IGeuRealisationSTBV {
    return {
      ...new GeuRealisationSTBV(),
      id: this.editForm.get(['id'])!.value,
      nbCamions: this.editForm.get(['nbCamions'])!.value,
      volume: this.editForm.get(['volume'])!.value,
      energie: this.editForm.get(['energie'])!.value,
      geustbvId: this.editForm.get(['geustbvId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuRealisationSTBV>>): void {
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

  trackById(index: number, item: IGeuSTBV): any {
    return item.id;
  }
}
