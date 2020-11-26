import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuPrevisionSTBVDetailComponent } from 'app/entities/geu-prevision-stbv/geu-prevision-stbv-detail.component';
import { GeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

describe('Component Tests', () => {
  describe('GeuPrevisionSTBV Management Detail Component', () => {
    let comp: GeuPrevisionSTBVDetailComponent;
    let fixture: ComponentFixture<GeuPrevisionSTBVDetailComponent>;
    const route = ({ data: of({ geuPrevisionSTBV: new GeuPrevisionSTBV(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPrevisionSTBVDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuPrevisionSTBVDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuPrevisionSTBVDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuPrevisionSTBV on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuPrevisionSTBV).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
