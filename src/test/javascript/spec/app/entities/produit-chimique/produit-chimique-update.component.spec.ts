import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { ProduitChimiqueUpdateComponent } from 'app/entities/produit-chimique/produit-chimique-update.component';
import { ProduitChimiqueService } from 'app/entities/produit-chimique/produit-chimique.service';
import { ProduitChimique } from 'app/shared/model/produit-chimique.model';

describe('Component Tests', () => {
  describe('ProduitChimique Management Update Component', () => {
    let comp: ProduitChimiqueUpdateComponent;
    let fixture: ComponentFixture<ProduitChimiqueUpdateComponent>;
    let service: ProduitChimiqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ProduitChimiqueUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ProduitChimiqueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProduitChimiqueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProduitChimiqueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProduitChimique(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProduitChimique();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
