import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoSecteurUpdateComponent } from 'app/entities/geo-secteur/geo-secteur-update.component';
import { GeoSecteurService } from 'app/entities/geo-secteur/geo-secteur.service';
import { GeoSecteur } from 'app/shared/model/geo-secteur.model';

describe('Component Tests', () => {
  describe('GeoSecteur Management Update Component', () => {
    let comp: GeoSecteurUpdateComponent;
    let fixture: ComponentFixture<GeoSecteurUpdateComponent>;
    let service: GeoSecteurService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoSecteurUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoSecteurUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoSecteurUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoSecteurService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoSecteur(123);
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
        const entity = new GeoSecteur();
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
