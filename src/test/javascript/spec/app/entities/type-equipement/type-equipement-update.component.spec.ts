import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeEquipementUpdateComponent } from 'app/entities/type-equipement/type-equipement-update.component';
import { TypeEquipementService } from 'app/entities/type-equipement/type-equipement.service';
import { TypeEquipement } from 'app/shared/model/type-equipement.model';

describe('Component Tests', () => {
  describe('TypeEquipement Management Update Component', () => {
    let comp: TypeEquipementUpdateComponent;
    let fixture: ComponentFixture<TypeEquipementUpdateComponent>;
    let service: TypeEquipementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeEquipementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeEquipementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeEquipementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeEquipementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeEquipement(123);
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
        const entity = new TypeEquipement();
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
