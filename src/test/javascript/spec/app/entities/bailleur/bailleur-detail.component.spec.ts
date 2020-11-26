import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { BailleurDetailComponent } from 'app/entities/bailleur/bailleur-detail.component';
import { Bailleur } from 'app/shared/model/bailleur.model';

describe('Component Tests', () => {
  describe('Bailleur Management Detail Component', () => {
    let comp: BailleurDetailComponent;
    let fixture: ComponentFixture<BailleurDetailComponent>;
    const route = ({ data: of({ bailleur: new Bailleur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [BailleurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BailleurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BailleurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bailleur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bailleur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
