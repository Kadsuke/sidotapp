import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { ProduitChimiqueDetailComponent } from 'app/entities/produit-chimique/produit-chimique-detail.component';
import { ProduitChimique } from 'app/shared/model/produit-chimique.model';

describe('Component Tests', () => {
  describe('ProduitChimique Management Detail Component', () => {
    let comp: ProduitChimiqueDetailComponent;
    let fixture: ComponentFixture<ProduitChimiqueDetailComponent>;
    const route = ({ data: of({ produitChimique: new ProduitChimique(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ProduitChimiqueDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ProduitChimiqueDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProduitChimiqueDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load produitChimique on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.produitChimique).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
