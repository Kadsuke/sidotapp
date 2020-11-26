import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoLocaliteDetailComponent } from 'app/entities/geo-localite/geo-localite-detail.component';
import { GeoLocalite } from 'app/shared/model/geo-localite.model';

describe('Component Tests', () => {
  describe('GeoLocalite Management Detail Component', () => {
    let comp: GeoLocaliteDetailComponent;
    let fixture: ComponentFixture<GeoLocaliteDetailComponent>;
    const route = ({ data: of({ geoLocalite: new GeoLocalite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoLocaliteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoLocaliteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoLocaliteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoLocalite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoLocalite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
