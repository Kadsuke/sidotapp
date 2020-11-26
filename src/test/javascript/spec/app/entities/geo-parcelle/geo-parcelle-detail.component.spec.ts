import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoParcelleDetailComponent } from 'app/entities/geo-parcelle/geo-parcelle-detail.component';
import { GeoParcelle } from 'app/shared/model/geo-parcelle.model';

describe('Component Tests', () => {
  describe('GeoParcelle Management Detail Component', () => {
    let comp: GeoParcelleDetailComponent;
    let fixture: ComponentFixture<GeoParcelleDetailComponent>;
    const route = ({ data: of({ geoParcelle: new GeoParcelle(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoParcelleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoParcelleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoParcelleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoParcelle on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoParcelle).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
