import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoCommuneDetailComponent } from 'app/entities/geo-commune/geo-commune-detail.component';
import { GeoCommune } from 'app/shared/model/geo-commune.model';

describe('Component Tests', () => {
  describe('GeoCommune Management Detail Component', () => {
    let comp: GeoCommuneDetailComponent;
    let fixture: ComponentFixture<GeoCommuneDetailComponent>;
    const route = ({ data: of({ geoCommune: new GeoCommune(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoCommuneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoCommuneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoCommuneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoCommune on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoCommune).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
