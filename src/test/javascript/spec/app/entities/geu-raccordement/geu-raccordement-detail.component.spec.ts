import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuRaccordementDetailComponent } from 'app/entities/geu-raccordement/geu-raccordement-detail.component';
import { GeuRaccordement } from 'app/shared/model/geu-raccordement.model';

describe('Component Tests', () => {
  describe('GeuRaccordement Management Detail Component', () => {
    let comp: GeuRaccordementDetailComponent;
    let fixture: ComponentFixture<GeuRaccordementDetailComponent>;
    const route = ({ data: of({ geuRaccordement: new GeuRaccordement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRaccordementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuRaccordementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuRaccordementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuRaccordement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuRaccordement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
