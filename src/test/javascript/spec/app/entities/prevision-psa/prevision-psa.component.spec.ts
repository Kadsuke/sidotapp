import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { PrevisionPsaComponent } from 'app/entities/prevision-psa/prevision-psa.component';
import { PrevisionPsaService } from 'app/entities/prevision-psa/prevision-psa.service';
import { PrevisionPsa } from 'app/shared/model/prevision-psa.model';

describe('Component Tests', () => {
  describe('PrevisionPsa Management Component', () => {
    let comp: PrevisionPsaComponent;
    let fixture: ComponentFixture<PrevisionPsaComponent>;
    let service: PrevisionPsaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionPsaComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(PrevisionPsaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisionPsaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisionPsaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrevisionPsa(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.previsionPsas && comp.previsionPsas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrevisionPsa(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.previsionPsas && comp.previsionPsas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
