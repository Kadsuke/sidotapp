import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { NatureOuvrageComponent } from 'app/entities/nature-ouvrage/nature-ouvrage.component';
import { NatureOuvrageService } from 'app/entities/nature-ouvrage/nature-ouvrage.service';
import { NatureOuvrage } from 'app/shared/model/nature-ouvrage.model';

describe('Component Tests', () => {
  describe('NatureOuvrage Management Component', () => {
    let comp: NatureOuvrageComponent;
    let fixture: ComponentFixture<NatureOuvrageComponent>;
    let service: NatureOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [NatureOuvrageComponent],
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
        .overrideTemplate(NatureOuvrageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureOuvrageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureOuvrageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NatureOuvrage(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.natureOuvrages && comp.natureOuvrages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new NatureOuvrage(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.natureOuvrages && comp.natureOuvrages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
