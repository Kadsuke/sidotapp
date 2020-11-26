import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { GeuPrevisionSTBVComponent } from 'app/entities/geu-prevision-stbv/geu-prevision-stbv.component';
import { GeuPrevisionSTBVService } from 'app/entities/geu-prevision-stbv/geu-prevision-stbv.service';
import { GeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

describe('Component Tests', () => {
  describe('GeuPrevisionSTBV Management Component', () => {
    let comp: GeuPrevisionSTBVComponent;
    let fixture: ComponentFixture<GeuPrevisionSTBVComponent>;
    let service: GeuPrevisionSTBVService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPrevisionSTBVComponent],
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
        .overrideTemplate(GeuPrevisionSTBVComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuPrevisionSTBVComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuPrevisionSTBVService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeuPrevisionSTBV(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.geuPrevisionSTBVS && comp.geuPrevisionSTBVS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeuPrevisionSTBV(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.geuPrevisionSTBVS && comp.geuPrevisionSTBVS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
