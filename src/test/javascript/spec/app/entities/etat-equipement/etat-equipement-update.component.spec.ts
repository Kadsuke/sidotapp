import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatEquipementUpdateComponent } from 'app/entities/etat-equipement/etat-equipement-update.component';
import { EtatEquipementService } from 'app/entities/etat-equipement/etat-equipement.service';
import { EtatEquipement } from 'app/shared/model/etat-equipement.model';

describe('Component Tests', () => {
  describe('EtatEquipement Management Update Component', () => {
    let comp: EtatEquipementUpdateComponent;
    let fixture: ComponentFixture<EtatEquipementUpdateComponent>;
    let service: EtatEquipementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatEquipementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EtatEquipementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EtatEquipementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EtatEquipementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EtatEquipement(123);
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
        const entity = new EtatEquipement();
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
