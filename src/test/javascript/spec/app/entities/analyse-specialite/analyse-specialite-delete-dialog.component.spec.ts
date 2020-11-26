import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SidotTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { AnalyseSpecialiteDeleteDialogComponent } from 'app/entities/analyse-specialite/analyse-specialite-delete-dialog.component';
import { AnalyseSpecialiteService } from 'app/entities/analyse-specialite/analyse-specialite.service';

describe('Component Tests', () => {
  describe('AnalyseSpecialite Management Delete Component', () => {
    let comp: AnalyseSpecialiteDeleteDialogComponent;
    let fixture: ComponentFixture<AnalyseSpecialiteDeleteDialogComponent>;
    let service: AnalyseSpecialiteService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [AnalyseSpecialiteDeleteDialogComponent],
      })
        .overrideTemplate(AnalyseSpecialiteDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AnalyseSpecialiteDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AnalyseSpecialiteService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
