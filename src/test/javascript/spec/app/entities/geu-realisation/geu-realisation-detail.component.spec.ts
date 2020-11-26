import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuRealisationDetailComponent } from 'app/entities/geu-realisation/geu-realisation-detail.component';
import { GeuRealisation } from 'app/shared/model/geu-realisation.model';

describe('Component Tests', () => {
  describe('GeuRealisation Management Detail Component', () => {
    let comp: GeuRealisationDetailComponent;
    let fixture: ComponentFixture<GeuRealisationDetailComponent>;
    const route = ({ data: of({ geuRealisation: new GeuRealisation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRealisationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuRealisationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuRealisationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuRealisation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuRealisation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
