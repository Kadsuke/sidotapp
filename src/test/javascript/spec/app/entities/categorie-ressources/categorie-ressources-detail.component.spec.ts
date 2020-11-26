import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { CategorieRessourcesDetailComponent } from 'app/entities/categorie-ressources/categorie-ressources-detail.component';
import { CategorieRessources } from 'app/shared/model/categorie-ressources.model';

describe('Component Tests', () => {
  describe('CategorieRessources Management Detail Component', () => {
    let comp: CategorieRessourcesDetailComponent;
    let fixture: ComponentFixture<CategorieRessourcesDetailComponent>;
    const route = ({ data: of({ categorieRessources: new CategorieRessources(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CategorieRessourcesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CategorieRessourcesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CategorieRessourcesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load categorieRessources on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.categorieRessources).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
