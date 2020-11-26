import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeHabitationDetailComponent } from 'app/entities/type-habitation/type-habitation-detail.component';
import { TypeHabitation } from 'app/shared/model/type-habitation.model';

describe('Component Tests', () => {
  describe('TypeHabitation Management Detail Component', () => {
    let comp: TypeHabitationDetailComponent;
    let fixture: ComponentFixture<TypeHabitationDetailComponent>;
    const route = ({ data: of({ typeHabitation: new TypeHabitation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeHabitationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeHabitationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeHabitationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeHabitation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeHabitation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
