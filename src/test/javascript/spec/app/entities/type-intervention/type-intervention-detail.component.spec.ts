import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeInterventionDetailComponent } from 'app/entities/type-intervention/type-intervention-detail.component';
import { TypeIntervention } from 'app/shared/model/type-intervention.model';

describe('Component Tests', () => {
  describe('TypeIntervention Management Detail Component', () => {
    let comp: TypeInterventionDetailComponent;
    let fixture: ComponentFixture<TypeInterventionDetailComponent>;
    const route = ({ data: of({ typeIntervention: new TypeIntervention(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeInterventionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeInterventionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeInterventionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeIntervention on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeIntervention).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
