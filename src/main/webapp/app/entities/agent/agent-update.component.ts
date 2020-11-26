import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAgent, Agent } from 'app/shared/model/agent.model';
import { AgentService } from './agent.service';
import { ISite } from 'app/shared/model/site.model';
import { SiteService } from 'app/entities/site/site.service';

@Component({
  selector: 'jhi-agent-update',
  templateUrl: './agent-update.component.html',
})
export class AgentUpdateComponent implements OnInit {
  isSaving = false;
  sites: ISite[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [],
    numero: [],
    role: [],
    siteId: [],
  });

  constructor(
    protected agentService: AgentService,
    protected siteService: SiteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ agent }) => {
      this.updateForm(agent);

      this.siteService.query().subscribe((res: HttpResponse<ISite[]>) => (this.sites = res.body || []));
    });
  }

  updateForm(agent: IAgent): void {
    this.editForm.patchValue({
      id: agent.id,
      nom: agent.nom,
      numero: agent.numero,
      role: agent.role,
      siteId: agent.siteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const agent = this.createFromForm();
    if (agent.id !== undefined) {
      this.subscribeToSaveResponse(this.agentService.update(agent));
    } else {
      this.subscribeToSaveResponse(this.agentService.create(agent));
    }
  }

  private createFromForm(): IAgent {
    return {
      ...new Agent(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      role: this.editForm.get(['role'])!.value,
      siteId: this.editForm.get(['siteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgent>>): void {
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

  trackById(index: number, item: ISite): any {
    return item.id;
  }
}
