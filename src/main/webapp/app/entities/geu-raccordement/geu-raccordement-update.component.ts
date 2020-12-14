import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuRaccordement, GeuRaccordement } from 'app/shared/model/geu-raccordement.model';
import { GeuRaccordementService } from './geu-raccordement.service';
import { IGeoParcelle } from 'app/shared/model/geo-parcelle.model';
import { GeoParcelleService } from 'app/entities/geo-parcelle/geo-parcelle.service';
import { IAgent } from 'app/shared/model/agent.model';
import { AgentService } from 'app/entities/agent/agent.service';
import { ITacherons } from 'app/shared/model/tacherons.model';
import { TacheronsService } from 'app/entities/tacherons/tacherons.service';
import { IGeuUsage } from 'app/shared/model/geu-usage.model';
import { GeuUsageService } from 'app/entities/geu-usage/geu-usage.service';

type SelectableEntity = IGeoParcelle | IAgent | ITacherons | IGeuUsage;

@Component({
  selector: 'jhi-geu-raccordement-update',
  templateUrl: './geu-raccordement-update.component.html',
})
export class GeuRaccordementUpdateComponent implements OnInit {
  isSaving = false;
  geoparcelles: IGeoParcelle[] = [];
  agents: IAgent[] = [];
  tacherons: ITacherons[] = [];
  geuusages: IGeuUsage[] = [];

  editForm = this.fb.group({
    id: [],
    numAbonnement: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    proprietaireParacelle: [null, [Validators.required]],
    entreprise: [null, [Validators.required]],
    autreUsage: [null, [Validators.required]],
    geoparcelleId: [],
    agentId: [],
    tacheronsId: [],
    geuusageId: [],
  });

  constructor(
    protected geuRaccordementService: GeuRaccordementService,
    protected geoParcelleService: GeoParcelleService,
    protected agentService: AgentService,
    protected tacheronsService: TacheronsService,
    protected geuUsageService: GeuUsageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuRaccordement }) => {
      this.updateForm(geuRaccordement);

      this.geoParcelleService.query().subscribe((res: HttpResponse<IGeoParcelle[]>) => (this.geoparcelles = res.body || []));

      this.agentService.query().subscribe((res: HttpResponse<IAgent[]>) => (this.agents = res.body || []));

      this.tacheronsService.query().subscribe((res: HttpResponse<ITacherons[]>) => (this.tacherons = res.body || []));

      this.geuUsageService.query().subscribe((res: HttpResponse<IGeuUsage[]>) => (this.geuusages = res.body || []));
    });
  }

  updateForm(geuRaccordement: IGeuRaccordement): void {
    this.editForm.patchValue({
      id: geuRaccordement.id,
      numAbonnement: geuRaccordement.numAbonnement,
      nom: geuRaccordement.nom,
      prenom: geuRaccordement.prenom,
      adresse: geuRaccordement.adresse,
      proprietaireParacelle: geuRaccordement.proprietaireParacelle,
      entreprise: geuRaccordement.entreprise,
      autreUsage: geuRaccordement.autreUsage,
      geoparcelleId: geuRaccordement.geoparcelleId,
      agentId: geuRaccordement.agentId,
      tacheronsId: geuRaccordement.tacheronsId,
      geuusageId: geuRaccordement.geuusageId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuRaccordement = this.createFromForm();
    if (geuRaccordement.id !== undefined) {
      this.subscribeToSaveResponse(this.geuRaccordementService.update(geuRaccordement));
    } else {
      this.subscribeToSaveResponse(this.geuRaccordementService.create(geuRaccordement));
    }
  }

  private createFromForm(): IGeuRaccordement {
    return {
      ...new GeuRaccordement(),
      id: this.editForm.get(['id'])!.value,
      numAbonnement: this.editForm.get(['numAbonnement'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      proprietaireParacelle: this.editForm.get(['proprietaireParacelle'])!.value,
      entreprise: this.editForm.get(['entreprise'])!.value,
      autreUsage: this.editForm.get(['autreUsage'])!.value,
      geoparcelleId: this.editForm.get(['geoparcelleId'])!.value,
      agentId: this.editForm.get(['agentId'])!.value,
      tacheronsId: this.editForm.get(['tacheronsId'])!.value,
      geuusageId: this.editForm.get(['geuusageId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuRaccordement>>): void {
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
