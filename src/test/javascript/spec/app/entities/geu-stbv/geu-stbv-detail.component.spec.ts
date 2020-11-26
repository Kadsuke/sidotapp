import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuSTBVDetailComponent } from 'app/entities/geu-stbv/geu-stbv-detail.component';
import { GeuSTBV } from 'app/shared/model/geu-stbv.model';

describe('Component Tests', () => {
  describe('GeuSTBV Management Detail Component', () => {
    let comp: GeuSTBVDetailComponent;
    let fixture: ComponentFixture<GeuSTBVDetailComponent>;
    const route = ({ data: of({ geuSTBV: new GeuSTBV(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuSTBVDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuSTBVDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuSTBVDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuSTBV on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuSTBV).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
