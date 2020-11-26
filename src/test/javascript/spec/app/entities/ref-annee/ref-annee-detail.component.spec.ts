import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefAnneeDetailComponent } from 'app/entities/ref-annee/ref-annee-detail.component';
import { RefAnnee } from 'app/shared/model/ref-annee.model';

describe('Component Tests', () => {
  describe('RefAnnee Management Detail Component', () => {
    let comp: RefAnneeDetailComponent;
    let fixture: ComponentFixture<RefAnneeDetailComponent>;
    const route = ({ data: of({ refAnnee: new RefAnnee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefAnneeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RefAnneeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RefAnneeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load refAnnee on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.refAnnee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
