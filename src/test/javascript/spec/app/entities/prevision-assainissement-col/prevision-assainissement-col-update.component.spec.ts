import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrevisionAssainissementColUpdateComponent } from 'app/entities/prevision-assainissement-col/prevision-assainissement-col-update.component';
import { PrevisionAssainissementColService } from 'app/entities/prevision-assainissement-col/prevision-assainissement-col.service';
import { PrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';

describe('Component Tests', () => {
  describe('PrevisionAssainissementCol Management Update Component', () => {
    let comp: PrevisionAssainissementColUpdateComponent;
    let fixture: ComponentFixture<PrevisionAssainissementColUpdateComponent>;
    let service: PrevisionAssainissementColService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionAssainissementColUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrevisionAssainissementColUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisionAssainissementColUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisionAssainissementColService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisionAssainissementCol(123);
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
        const entity = new PrevisionAssainissementCol();
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
