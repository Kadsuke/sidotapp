import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { SourceApprovEpUpdateComponent } from 'app/entities/source-approv-ep/source-approv-ep-update.component';
import { SourceApprovEpService } from 'app/entities/source-approv-ep/source-approv-ep.service';
import { SourceApprovEp } from 'app/shared/model/source-approv-ep.model';

describe('Component Tests', () => {
  describe('SourceApprovEp Management Update Component', () => {
    let comp: SourceApprovEpUpdateComponent;
    let fixture: ComponentFixture<SourceApprovEpUpdateComponent>;
    let service: SourceApprovEpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [SourceApprovEpUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SourceApprovEpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SourceApprovEpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SourceApprovEpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SourceApprovEp(123);
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
        const entity = new SourceApprovEp();
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
