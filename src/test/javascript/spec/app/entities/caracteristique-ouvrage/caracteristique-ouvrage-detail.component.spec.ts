import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { CaracteristiqueOuvrageDetailComponent } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage-detail.component';
import { CaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

describe('Component Tests', () => {
  describe('CaracteristiqueOuvrage Management Detail Component', () => {
    let comp: CaracteristiqueOuvrageDetailComponent;
    let fixture: ComponentFixture<CaracteristiqueOuvrageDetailComponent>;
    const route = ({ data: of({ caracteristiqueOuvrage: new CaracteristiqueOuvrage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CaracteristiqueOuvrageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CaracteristiqueOuvrageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaracteristiqueOuvrageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load caracteristiqueOuvrage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.caracteristiqueOuvrage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
