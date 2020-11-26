import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { AnalyseParametreDetailComponent } from 'app/entities/analyse-parametre/analyse-parametre-detail.component';
import { AnalyseParametre } from 'app/shared/model/analyse-parametre.model';

describe('Component Tests', () => {
  describe('AnalyseParametre Management Detail Component', () => {
    let comp: AnalyseParametreDetailComponent;
    let fixture: ComponentFixture<AnalyseParametreDetailComponent>;
    const route = ({ data: of({ analyseParametre: new AnalyseParametre(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [AnalyseParametreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AnalyseParametreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnalyseParametreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load analyseParametre on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.analyseParametre).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
