import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypePlainteDetailComponent } from 'app/entities/type-plainte/type-plainte-detail.component';
import { TypePlainte } from 'app/shared/model/type-plainte.model';

describe('Component Tests', () => {
  describe('TypePlainte Management Detail Component', () => {
    let comp: TypePlainteDetailComponent;
    let fixture: ComponentFixture<TypePlainteDetailComponent>;
    const route = ({ data: of({ typePlainte: new TypePlainte(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypePlainteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypePlainteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypePlainteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typePlainte on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typePlainte).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
