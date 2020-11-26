import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuSTEPDetailComponent } from 'app/entities/geu-step/geu-step-detail.component';
import { GeuSTEP } from 'app/shared/model/geu-step.model';

describe('Component Tests', () => {
  describe('GeuSTEP Management Detail Component', () => {
    let comp: GeuSTEPDetailComponent;
    let fixture: ComponentFixture<GeuSTEPDetailComponent>;
    const route = ({ data: of({ geuSTEP: new GeuSTEP(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuSTEPDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuSTEPDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuSTEPDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuSTEP on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuSTEP).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
