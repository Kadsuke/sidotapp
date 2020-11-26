import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { CategorieRessourcesComponent } from 'app/entities/categorie-ressources/categorie-ressources.component';
import { CategorieRessourcesService } from 'app/entities/categorie-ressources/categorie-ressources.service';
import { CategorieRessources } from 'app/shared/model/categorie-ressources.model';

describe('Component Tests', () => {
  describe('CategorieRessources Management Component', () => {
    let comp: CategorieRessourcesComponent;
    let fixture: ComponentFixture<CategorieRessourcesComponent>;
    let service: CategorieRessourcesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CategorieRessourcesComponent],
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
        .overrideTemplate(CategorieRessourcesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieRessourcesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieRessourcesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CategorieRessources(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.categorieRessources && comp.categorieRessources[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CategorieRessources(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.categorieRessources && comp.categorieRessources[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
