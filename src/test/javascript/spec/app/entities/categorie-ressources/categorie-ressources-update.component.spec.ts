import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { CategorieRessourcesUpdateComponent } from 'app/entities/categorie-ressources/categorie-ressources-update.component';
import { CategorieRessourcesService } from 'app/entities/categorie-ressources/categorie-ressources.service';
import { CategorieRessources } from 'app/shared/model/categorie-ressources.model';

describe('Component Tests', () => {
  describe('CategorieRessources Management Update Component', () => {
    let comp: CategorieRessourcesUpdateComponent;
    let fixture: ComponentFixture<CategorieRessourcesUpdateComponent>;
    let service: CategorieRessourcesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CategorieRessourcesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CategorieRessourcesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CategorieRessourcesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CategorieRessourcesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CategorieRessources(123);
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
        const entity = new CategorieRessources();
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
