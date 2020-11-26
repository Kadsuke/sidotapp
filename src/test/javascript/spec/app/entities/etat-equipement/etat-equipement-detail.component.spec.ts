import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatEquipementDetailComponent } from 'app/entities/etat-equipement/etat-equipement-detail.component';
import { EtatEquipement } from 'app/shared/model/etat-equipement.model';

describe('Component Tests', () => {
  describe('EtatEquipement Management Detail Component', () => {
    let comp: EtatEquipementDetailComponent;
    let fixture: ComponentFixture<EtatEquipementDetailComponent>;
    const route = ({ data: of({ etatEquipement: new EtatEquipement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatEquipementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EtatEquipementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EtatEquipementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load etatEquipement on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.etatEquipement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
