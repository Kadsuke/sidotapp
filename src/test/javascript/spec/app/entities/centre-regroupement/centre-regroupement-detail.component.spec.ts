import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { CentreRegroupementDetailComponent } from 'app/entities/centre-regroupement/centre-regroupement-detail.component';
import { CentreRegroupement } from 'app/shared/model/centre-regroupement.model';

describe('Component Tests', () => {
  describe('CentreRegroupement Management Detail Component', () => {
    let comp: CentreRegroupementDetailComponent;
    let fixture: ComponentFixture<CentreRegroupementDetailComponent>;
    const route = ({ data: of({ centreRegroupement: new CentreRegroupement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CentreRegroupementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CentreRegroupementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CentreRegroupementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load centreRegroupement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.centreRegroupement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
