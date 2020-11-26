import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrestataireDetailComponent } from 'app/entities/prestataire/prestataire-detail.component';
import { Prestataire } from 'app/shared/model/prestataire.model';

describe('Component Tests', () => {
  describe('Prestataire Management Detail Component', () => {
    let comp: PrestataireDetailComponent;
    let fixture: ComponentFixture<PrestataireDetailComponent>;
    const route = ({ data: of({ prestataire: new Prestataire(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrestataireDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrestataireDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrestataireDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load prestataire on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.prestataire).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
