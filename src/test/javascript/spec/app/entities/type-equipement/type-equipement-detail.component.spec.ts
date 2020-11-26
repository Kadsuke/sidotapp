import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeEquipementDetailComponent } from 'app/entities/type-equipement/type-equipement-detail.component';
import { TypeEquipement } from 'app/shared/model/type-equipement.model';

describe('Component Tests', () => {
  describe('TypeEquipement Management Detail Component', () => {
    let comp: TypeEquipementDetailComponent;
    let fixture: ComponentFixture<TypeEquipementDetailComponent>;
    const route = ({ data: of({ typeEquipement: new TypeEquipement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeEquipementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeEquipementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeEquipementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeEquipement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeEquipement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
