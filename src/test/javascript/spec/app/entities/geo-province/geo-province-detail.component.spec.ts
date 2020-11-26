import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoProvinceDetailComponent } from 'app/entities/geo-province/geo-province-detail.component';
import { GeoProvince } from 'app/shared/model/geo-province.model';

describe('Component Tests', () => {
  describe('GeoProvince Management Detail Component', () => {
    let comp: GeoProvinceDetailComponent;
    let fixture: ComponentFixture<GeoProvinceDetailComponent>;
    const route = ({ data: of({ geoProvince: new GeoProvince(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoProvinceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoProvinceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoProvinceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoProvince on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoProvince).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
