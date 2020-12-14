import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPrevisionPsa, PrevisionPsa } from 'app/shared/model/prevision-psa.model';
import { PrevisionPsaService } from './prevision-psa.service';
import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from 'app/entities/centre/centre.service';
import { IRefAnnee } from 'app/shared/model/ref-annee.model';
import { RefAnneeService } from 'app/entities/ref-annee/ref-annee.service';

type SelectableEntity = ICentre | IRefAnnee;

@Component({
  selector: 'jhi-prevision-psa-update',
  templateUrl: './prevision-psa-update.component.html',
})
export class PrevisionPsaUpdateComponent implements OnInit {
  isSaving = false;
  centres: ICentre[] = [];
  refannees: IRefAnnee[] = [];

  editForm = this.fb.group({
    id: [],
    elabore: [null, [Validators.required]],
    miseEnOeuvre: [null, [Validators.required]],
    centreId: [],
    refAnneeId: [],
  });

  constructor(
    protected previsionPsaService: PrevisionPsaService,
    protected centreService: CentreService,
    protected refAnneeService: RefAnneeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsionPsa }) => {
      this.updateForm(previsionPsa);

      this.centreService
        .query({ filter: 'previsionpsa-is-null' })
        .pipe(
          map((res: HttpResponse<ICentre[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICentre[]) => {
          if (!previsionPsa.centreId) {
            this.centres = resBody;
          } else {
            this.centreService
              .find(previsionPsa.centreId)
              .pipe(
                map((subRes: HttpResponse<ICentre>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICentre[]) => (this.centres = concatRes));
          }
        });

      this.refAnneeService
        .query({ filter: 'previsionpsa-is-null' })
        .pipe(
          map((res: HttpResponse<IRefAnnee[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRefAnnee[]) => {
          if (!previsionPsa.refAnneeId) {
            this.refannees = resBody;
          } else {
            this.refAnneeService
              .find(previsionPsa.refAnneeId)
              .pipe(
                map((subRes: HttpResponse<IRefAnnee>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRefAnnee[]) => (this.refannees = concatRes));
          }
        });
    });
  }

  updateForm(previsionPsa: IPrevisionPsa): void {
    this.editForm.patchValue({
      id: previsionPsa.id,
      elabore: previsionPsa.elabore,
      miseEnOeuvre: previsionPsa.miseEnOeuvre,
      centreId: previsionPsa.centreId,
      refAnneeId: previsionPsa.refAnneeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const previsionPsa = this.createFromForm();
    if (previsionPsa.id !== undefined) {
      this.subscribeToSaveResponse(this.previsionPsaService.update(previsionPsa));
    } else {
      this.subscribeToSaveResponse(this.previsionPsaService.create(previsionPsa));
    }
  }

  private createFromForm(): IPrevisionPsa {
    return {
      ...new PrevisionPsa(),
      id: this.editForm.get(['id'])!.value,
      elabore: this.editForm.get(['elabore'])!.value,
      miseEnOeuvre: this.editForm.get(['miseEnOeuvre'])!.value,
      centreId: this.editForm.get(['centreId'])!.value,
      refAnneeId: this.editForm.get(['refAnneeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrevisionPsa>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
