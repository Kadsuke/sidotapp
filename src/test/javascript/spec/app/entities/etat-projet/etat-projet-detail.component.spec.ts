import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatProjetDetailComponent } from 'app/entities/etat-projet/etat-projet-detail.component';
import { EtatProjet } from 'app/shared/model/etat-projet.model';

describe('Component Tests', () => {
  describe('EtatProjet Management Detail Component', () => {
    let comp: EtatProjetDetailComponent;
    let fixture: ComponentFixture<EtatProjetDetailComponent>;
    const route = ({ data: of({ etatProjet: new EtatProjet(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatProjetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EtatProjetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EtatProjetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load etatProjet on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.etatProjet).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
