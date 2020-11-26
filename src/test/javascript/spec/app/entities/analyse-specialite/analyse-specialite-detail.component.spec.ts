import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { AnalyseSpecialiteDetailComponent } from 'app/entities/analyse-specialite/analyse-specialite-detail.component';
import { AnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';

describe('Component Tests', () => {
  describe('AnalyseSpecialite Management Detail Component', () => {
    let comp: AnalyseSpecialiteDetailComponent;
    let fixture: ComponentFixture<AnalyseSpecialiteDetailComponent>;
    const route = ({ data: of({ analyseSpecialite: new AnalyseSpecialite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [AnalyseSpecialiteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AnalyseSpecialiteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnalyseSpecialiteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load analyseSpecialite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.analyseSpecialite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
