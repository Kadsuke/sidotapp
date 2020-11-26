import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoTypeCommuneDetailComponent } from 'app/entities/geo-type-commune/geo-type-commune-detail.component';
import { GeoTypeCommune } from 'app/shared/model/geo-type-commune.model';

describe('Component Tests', () => {
  describe('GeoTypeCommune Management Detail Component', () => {
    let comp: GeoTypeCommuneDetailComponent;
    let fixture: ComponentFixture<GeoTypeCommuneDetailComponent>;
    const route = ({ data: of({ geoTypeCommune: new GeoTypeCommune(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoTypeCommuneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoTypeCommuneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoTypeCommuneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoTypeCommune on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoTypeCommune).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
