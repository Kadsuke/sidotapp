import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ModeEvacuationEauUseeService } from './mode-evacuation-eau-usee.service';
import { ModeEvacuationEauUseeDeleteDialogComponent } from './mode-evacuation-eau-usee-delete-dialog.component';

@Component({
  selector: 'jhi-mode-evacuation-eau-usee',
  templateUrl: './mode-evacuation-eau-usee.component.html',
})
export class ModeEvacuationEauUseeComponent implements OnInit, OnDestroy {
  modeEvacuationEauUsees?: IModeEvacuationEauUsee[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected modeEvacuationEauUseeService: ModeEvacuationEauUseeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    const pageToLoad: number = page || this.page || 1;

    this.modeEvacuationEauUseeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IModeEvacuationEauUsee[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
    this.registerChangeInModeEvacuationEauUsees();
  }

  protected handleNavigation(): void {
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IModeEvacuationEauUsee): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInModeEvacuationEauUsees(): void {
    this.eventSubscriber = this.eventManager.subscribe('modeEvacuationEauUseeListModification', () => this.loadPage());
  }

  delete(modeEvacuationEauUsee: IModeEvacuationEauUsee): void {
    const modalRef = this.modalService.open(ModeEvacuationEauUseeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.modeEvacuationEauUsee = modeEvacuationEauUsee;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IModeEvacuationEauUsee[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/mode-evacuation-eau-usee'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.modeEvacuationEauUsees = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }
}
