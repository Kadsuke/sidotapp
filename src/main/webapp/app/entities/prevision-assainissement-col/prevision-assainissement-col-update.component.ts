import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPrevisionAssainissementCol, PrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';
import { PrevisionAssainissementColService } from './prevision-assainissement-col.service';
import { IRefAnnee } from 'app/shared/model/ref-annee.model';
import { RefAnneeService } from 'app/entities/ref-annee/ref-annee.service';
import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from 'app/entities/centre/centre.service';

type SelectableEntity = IRefAnnee | ICentre;

@Component({
  selector: 'jhi-prevision-assainissement-col-update',
  templateUrl: './prevision-assainissement-col-update.component.html',
})
export class PrevisionAssainissementColUpdateComponent implements OnInit {
  isSaving = false;
  refannees: IRefAnnee[] = [];
  centres: ICentre[] = [];

  editForm = this.fb.group({
    id: [],
    nbStep: [null, [Validators.required]],
    nbStbv: [null, [Validators.required]],
    reseaux: [null, [Validators.required]],
    nbRaccordement: [null, [Validators.required]],
    refanneeId: [],
    centreId: [],
  });

  constructor(
    protected previsionAssainissementColService: PrevisionAssainissementColService,
    protected refAnneeService: RefAnneeService,
    protected centreService: CentreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsionAssainissementCol }) => {
      this.updateForm(previsionAssainissementCol);

      this.refAnneeService
        .query({ filter: 'previsionassainissementcol-is-null' })
        .pipe(
          map((res: HttpResponse<IRefAnnee[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRefAnnee[]) => {
          if (!previsionAssainissementCol.refanneeId) {
            this.refannees = resBody;
          } else {
            this.refAnneeService
              .find(previsionAssainissementCol.refanneeId)
              .pipe(
                map((subRes: HttpResponse<IRefAnnee>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRefAnnee[]) => (this.refannees = concatRes));
          }
        });

      this.centreService
        .query({ filter: 'previsionassainissementcol-is-null' })
        .pipe(
          map((res: HttpResponse<ICentre[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICentre[]) => {
          if (!previsionAssainissementCol.centreId) {
            this.centres = resBody;
          } else {
            this.centreService
              .find(previsionAssainissementCol.centreId)
              .pipe(
                map((subRes: HttpResponse<ICentre>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICentre[]) => (this.centres = concatRes));
          }
        });
    });
  }

  updateForm(previsionAssainissementCol: IPrevisionAssainissementCol): void {
    this.editForm.patchValue({
      id: previsionAssainissementCol.id,
      nbStep: previsionAssainissementCol.nbStep,
      nbStbv: previsionAssainissementCol.nbStbv,
      reseaux: previsionAssainissementCol.reseaux,
      nbRaccordement: previsionAssainissementCol.nbRaccordement,
      refanneeId: previsionAssainissementCol.refanneeId,
      centreId: previsionAssainissementCol.centreId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const previsionAssainissementCol = this.createFromForm();
    if (previsionAssainissementCol.id !== undefined) {
      this.subscribeToSaveResponse(this.previsionAssainissementColService.update(previsionAssainissementCol));
    } else {
      this.subscribeToSaveResponse(this.previsionAssainissementColService.create(previsionAssainissementCol));
    }
  }

  private createFromForm(): IPrevisionAssainissementCol {
    return {
      ...new PrevisionAssainissementCol(),
      id: this.editForm.get(['id'])!.value,
      nbStep: this.editForm.get(['nbStep'])!.value,
      nbStbv: this.editForm.get(['nbStbv'])!.value,
      reseaux: this.editForm.get(['reseaux'])!.value,
      nbRaccordement: this.editForm.get(['nbRaccordement'])!.value,
      refanneeId: this.editForm.get(['refanneeId'])!.value,
      centreId: this.editForm.get(['centreId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrevisionAssainissementCol>>): void {
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
