import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuRealisationSTBVDetailComponent } from 'app/entities/geu-realisation-stbv/geu-realisation-stbv-detail.component';
import { GeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';

describe('Component Tests', () => {
  describe('GeuRealisationSTBV Management Detail Component', () => {
    let comp: GeuRealisationSTBVDetailComponent;
    let fixture: ComponentFixture<GeuRealisationSTBVDetailComponent>;
    const route = ({ data: of({ geuRealisationSTBV: new GeuRealisationSTBV(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRealisationSTBVDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuRealisationSTBVDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuRealisationSTBVDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuRealisationSTBV on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuRealisationSTBV).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
