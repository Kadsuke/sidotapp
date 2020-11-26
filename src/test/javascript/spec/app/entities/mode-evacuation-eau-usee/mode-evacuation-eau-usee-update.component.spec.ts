import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { ModeEvacuationEauUseeUpdateComponent } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee-update.component';
import { ModeEvacuationEauUseeService } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee.service';
import { ModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';

describe('Component Tests', () => {
  describe('ModeEvacuationEauUsee Management Update Component', () => {
    let comp: ModeEvacuationEauUseeUpdateComponent;
    let fixture: ComponentFixture<ModeEvacuationEauUseeUpdateComponent>;
    let service: ModeEvacuationEauUseeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ModeEvacuationEauUseeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ModeEvacuationEauUseeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModeEvacuationEauUseeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModeEvacuationEauUseeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ModeEvacuationEauUsee(123);
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
        const entity = new ModeEvacuationEauUsee();
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
