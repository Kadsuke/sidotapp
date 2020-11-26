import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatOuvrageDetailComponent } from 'app/entities/etat-ouvrage/etat-ouvrage-detail.component';
import { EtatOuvrage } from 'app/shared/model/etat-ouvrage.model';

describe('Component Tests', () => {
  describe('EtatOuvrage Management Detail Component', () => {
    let comp: EtatOuvrageDetailComponent;
    let fixture: ComponentFixture<EtatOuvrageDetailComponent>;
    const route = ({ data: of({ etatOuvrage: new EtatOuvrage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatOuvrageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EtatOuvrageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EtatOuvrageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load etatOuvrage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.etatOuvrage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
