import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { CaracteristiqueOuvrageComponent } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage.component';
import { CaracteristiqueOuvrageService } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage.service';
import { CaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

describe('Component Tests', () => {
  describe('CaracteristiqueOuvrage Management Component', () => {
    let comp: CaracteristiqueOuvrageComponent;
    let fixture: ComponentFixture<CaracteristiqueOuvrageComponent>;
    let service: CaracteristiqueOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CaracteristiqueOuvrageComponent],
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
        .overrideTemplate(CaracteristiqueOuvrageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaracteristiqueOuvrageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaracteristiqueOuvrageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CaracteristiqueOuvrage(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.caracteristiqueOuvrages && comp.caracteristiqueOuvrages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CaracteristiqueOuvrage(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.caracteristiqueOuvrages && comp.caracteristiqueOuvrages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
