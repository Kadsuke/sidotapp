import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { BailleurUpdateComponent } from 'app/entities/bailleur/bailleur-update.component';
import { BailleurService } from 'app/entities/bailleur/bailleur.service';
import { Bailleur } from 'app/shared/model/bailleur.model';

describe('Component Tests', () => {
  describe('Bailleur Management Update Component', () => {
    let comp: BailleurUpdateComponent;
    let fixture: ComponentFixture<BailleurUpdateComponent>;
    let service: BailleurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [BailleurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BailleurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BailleurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BailleurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Bailleur(123);
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
        const entity = new Bailleur();
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
