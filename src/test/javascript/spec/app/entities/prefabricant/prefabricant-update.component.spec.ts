import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrefabricantUpdateComponent } from 'app/entities/prefabricant/prefabricant-update.component';
import { PrefabricantService } from 'app/entities/prefabricant/prefabricant.service';
import { Prefabricant } from 'app/shared/model/prefabricant.model';

describe('Component Tests', () => {
  describe('Prefabricant Management Update Component', () => {
    let comp: PrefabricantUpdateComponent;
    let fixture: ComponentFixture<PrefabricantUpdateComponent>;
    let service: PrefabricantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrefabricantUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrefabricantUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrefabricantUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrefabricantService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Prefabricant(123);
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
        const entity = new Prefabricant();
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
