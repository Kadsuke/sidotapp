import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuPrevisionSTBV, GeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';
import { GeuPrevisionSTBVService } from './geu-prevision-stbv.service';
import { IGeuSTBV } from 'app/shared/model/geu-stbv.model';
import { GeuSTBVService } from 'app/entities/geu-stbv/geu-stbv.service';

@Component({
  selector: 'jhi-geu-prevision-stbv-update',
  templateUrl: './geu-prevision-stbv-update.component.html',
})
export class GeuPrevisionSTBVUpdateComponent implements OnInit {
  isSaving = false;
  geustbvs: IGeuSTBV[] = [];

  editForm = this.fb.group({
    id: [],
    nbCamions: [null, [Validators.required]],
    volume: [null, [Validators.required]],
    energie: [null, [Validators.required]],
    geustbvId: [],
  });

  constructor(
    protected geuPrevisionSTBVService: GeuPrevisionSTBVService,
    protected geuSTBVService: GeuSTBVService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuPrevisionSTBV }) => {
      this.updateForm(geuPrevisionSTBV);

      this.geuSTBVService.query().subscribe((res: HttpResponse<IGeuSTBV[]>) => (this.geustbvs = res.body || []));
    });
  }

  updateForm(geuPrevisionSTBV: IGeuPrevisionSTBV): void {
    this.editForm.patchValue({
      id: geuPrevisionSTBV.id,
      nbCamions: geuPrevisionSTBV.nbCamions,
      volume: geuPrevisionSTBV.volume,
      energie: geuPrevisionSTBV.energie,
      geustbvId: geuPrevisionSTBV.geustbvId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuPrevisionSTBV = this.createFromForm();
    if (geuPrevisionSTBV.id !== undefined) {
      this.subscribeToSaveResponse(this.geuPrevisionSTBVService.update(geuPrevisionSTBV));
    } else {
      this.subscribeToSaveResponse(this.geuPrevisionSTBVService.create(geuPrevisionSTBV));
    }
  }

  private createFromForm(): IGeuPrevisionSTBV {
    return {
      ...new GeuPrevisionSTBV(),
      id: this.editForm.get(['id'])!.value,
      nbCamions: this.editForm.get(['nbCamions'])!.value,
      volume: this.editForm.get(['volume'])!.value,
      energie: this.editForm.get(['energie'])!.value,
      geustbvId: this.editForm.get(['geustbvId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuPrevisionSTBV>>): void {
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
