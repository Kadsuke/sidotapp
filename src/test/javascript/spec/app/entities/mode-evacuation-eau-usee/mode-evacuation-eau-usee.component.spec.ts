import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { SidotTestModule } from '../../../test.module';
import { ModeEvacuationEauUseeComponent } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee.component';
import { ModeEvacuationEauUseeService } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee.service';
import { ModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';

describe('Component Tests', () => {
  describe('ModeEvacuationEauUsee Management Component', () => {
    let comp: ModeEvacuationEauUseeComponent;
    let fixture: ComponentFixture<ModeEvacuationEauUseeComponent>;
    let service: ModeEvacuationEauUseeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ModeEvacuationEauUseeComponent],
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
        .overrideTemplate(ModeEvacuationEauUseeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeEvacuationEauUseeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeEvacuationEauUseeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModeEvacuationEauUsee(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modeEvacuationEauUsees && comp.modeEvacuationEauUsees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ModeEvacuationEauUsee(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.modeEvacuationEauUsees && comp.modeEvacuationEauUsees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
