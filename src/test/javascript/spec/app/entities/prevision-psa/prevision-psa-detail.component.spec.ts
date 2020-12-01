import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrevisionPsaDetailComponent } from 'app/entities/prevision-psa/prevision-psa-detail.component';
import { PrevisionPsa } from 'app/shared/model/prevision-psa.model';

describe('Component Tests', () => {
  describe('PrevisionPsa Management Detail Component', () => {
    let comp: PrevisionPsaDetailComponent;
    let fixture: ComponentFixture<PrevisionPsaDetailComponent>;
    const route = ({ data: of({ previsionPsa: new PrevisionPsa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionPsaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrevisionPsaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisionPsaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load previsionPsa on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.previsionPsa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
