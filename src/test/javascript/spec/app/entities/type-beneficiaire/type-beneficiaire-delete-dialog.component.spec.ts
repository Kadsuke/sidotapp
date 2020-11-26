import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SidotTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TypeBeneficiaireDeleteDialogComponent } from 'app/entities/type-beneficiaire/type-beneficiaire-delete-dialog.component';
import { TypeBeneficiaireService } from 'app/entities/type-beneficiaire/type-beneficiaire.service';

describe('Component Tests', () => {
  describe('TypeBeneficiaire Management Delete Component', () => {
    let comp: TypeBeneficiaireDeleteDialogComponent;
    let fixture: ComponentFixture<TypeBeneficiaireDeleteDialogComponent>;
    let service: TypeBeneficiaireService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeBeneficiaireDeleteDialogComponent],
      })
        .overrideTemplate(TypeBeneficiaireDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeBeneficiaireDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeBeneficiaireService);
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
