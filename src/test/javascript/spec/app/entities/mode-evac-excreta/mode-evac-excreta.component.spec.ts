import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { ModeEvacExcretaComponent } from 'app/entities/mode-evac-excreta/mode-evac-excreta.component';
import { ModeEvacExcretaService } from 'app/entities/mode-evac-excreta/mode-evac-excreta.service';
import { ModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';

describe('Component Tests', () => {
  describe('ModeEvacExcreta Management Component', () => {
    let comp: ModeEvacExcretaComponent;
    let fixture: ComponentFixture<ModeEvacExcretaComponent>;
    let service: ModeEvacExcretaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ModeEvacExcretaComponent],
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
        .overrideTemplate(ModeEvacExcretaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeEvacExcretaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeEvacExcretaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModeEvacExcreta(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modeEvacExcretas && comp.modeEvacExcretas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModeEvacExcreta(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modeEvacExcretas && comp.modeEvacExcretas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
