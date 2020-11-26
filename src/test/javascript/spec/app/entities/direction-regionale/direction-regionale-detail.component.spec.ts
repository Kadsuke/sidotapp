import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { DirectionRegionaleDetailComponent } from 'app/entities/direction-regionale/direction-regionale-detail.component';
import { DirectionRegionale } from 'app/shared/model/direction-regionale.model';

describe('Component Tests', () => {
  describe('DirectionRegionale Management Detail Component', () => {
    let comp: DirectionRegionaleDetailComponent;
    let fixture: ComponentFixture<DirectionRegionaleDetailComponent>;
    const route = ({ data: of({ directionRegionale: new DirectionRegionale(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [DirectionRegionaleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DirectionRegionaleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DirectionRegionaleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load directionRegionale on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.directionRegionale).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
