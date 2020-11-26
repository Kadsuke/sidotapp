import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoSectionDetailComponent } from 'app/entities/geo-section/geo-section-detail.component';
import { GeoSection } from 'app/shared/model/geo-section.model';

describe('Component Tests', () => {
  describe('GeoSection Management Detail Component', () => {
    let comp: GeoSectionDetailComponent;
    let fixture: ComponentFixture<GeoSectionDetailComponent>;
    const route = ({ data: of({ geoSection: new GeoSection(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoSectionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoSectionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoSectionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoSection on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoSection).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
