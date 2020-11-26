import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefMoisDetailComponent } from 'app/entities/ref-mois/ref-mois-detail.component';
import { RefMois } from 'app/shared/model/ref-mois.model';

describe('Component Tests', () => {
  describe('RefMois Management Detail Component', () => {
    let comp: RefMoisDetailComponent;
    let fixture: ComponentFixture<RefMoisDetailComponent>;
    const route = ({ data: of({ refMois: new RefMois(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefMoisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RefMoisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RefMoisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load refMois on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.refMois).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
