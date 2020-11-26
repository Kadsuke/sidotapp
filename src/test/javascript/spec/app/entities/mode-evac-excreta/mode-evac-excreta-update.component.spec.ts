import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { ModeEvacExcretaUpdateComponent } from 'app/entities/mode-evac-excreta/mode-evac-excreta-update.component';
import { ModeEvacExcretaService } from 'app/entities/mode-evac-excreta/mode-evac-excreta.service';
import { ModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';

describe('Component Tests', () => {
  describe('ModeEvacExcreta Management Update Component', () => {
    let comp: ModeEvacExcretaUpdateComponent;
    let fixture: ComponentFixture<ModeEvacExcretaUpdateComponent>;
    let service: ModeEvacExcretaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ModeEvacExcretaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ModeEvacExcretaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeEvacExcretaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeEvacExcretaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModeEvacExcreta(123);
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
        const entity = new ModeEvacExcreta();
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
