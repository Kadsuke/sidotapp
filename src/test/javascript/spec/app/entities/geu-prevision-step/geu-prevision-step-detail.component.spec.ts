import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuPrevisionSTEPDetailComponent } from 'app/entities/geu-prevision-step/geu-prevision-step-detail.component';
import { GeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';

describe('Component Tests', () => {
  describe('GeuPrevisionSTEP Management Detail Component', () => {
    let comp: GeuPrevisionSTEPDetailComponent;
    let fixture: ComponentFixture<GeuPrevisionSTEPDetailComponent>;
    const route = ({ data: of({ geuPrevisionSTEP: new GeuPrevisionSTEP(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPrevisionSTEPDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuPrevisionSTEPDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuPrevisionSTEPDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuPrevisionSTEP on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuPrevisionSTEP).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
