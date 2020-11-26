import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuPSADetailComponent } from 'app/entities/geu-psa/geu-psa-detail.component';
import { GeuPSA } from 'app/shared/model/geu-psa.model';

describe('Component Tests', () => {
  describe('GeuPSA Management Detail Component', () => {
    let comp: GeuPSADetailComponent;
    let fixture: ComponentFixture<GeuPSADetailComponent>;
    const route = ({ data: of({ geuPSA: new GeuPSA(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPSADetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuPSADetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuPSADetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuPSA on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuPSA).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
