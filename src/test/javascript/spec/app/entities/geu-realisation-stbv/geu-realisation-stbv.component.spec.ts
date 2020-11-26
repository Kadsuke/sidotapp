import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { GeuRealisationSTBVComponent } from 'app/entities/geu-realisation-stbv/geu-realisation-stbv.component';
import { GeuRealisationSTBVService } from 'app/entities/geu-realisation-stbv/geu-realisation-stbv.service';
import { GeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';

describe('Component Tests', () => {
  describe('GeuRealisationSTBV Management Component', () => {
    let comp: GeuRealisationSTBVComponent;
    let fixture: ComponentFixture<GeuRealisationSTBVComponent>;
    let service: GeuRealisationSTBVService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRealisationSTBVComponent],
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
        .overrideTemplate(GeuRealisationSTBVComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuRealisationSTBVComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuRealisationSTBVService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeuRealisationSTBV(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.geuRealisationSTBVS && comp.geuRealisationSTBVS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GeuRealisationSTBV(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.geuRealisationSTBVS && comp.geuRealisationSTBVS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
