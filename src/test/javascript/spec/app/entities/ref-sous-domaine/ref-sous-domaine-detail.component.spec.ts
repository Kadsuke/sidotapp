import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefSousDomaineDetailComponent } from 'app/entities/ref-sous-domaine/ref-sous-domaine-detail.component';
import { RefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';

describe('Component Tests', () => {
  describe('RefSousDomaine Management Detail Component', () => {
    let comp: RefSousDomaineDetailComponent;
    let fixture: ComponentFixture<RefSousDomaineDetailComponent>;
    const route = ({ data: of({ refSousDomaine: new RefSousDomaine(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefSousDomaineDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RefSousDomaineDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RefSousDomaineDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load refSousDomaine on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.refSousDomaine).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
