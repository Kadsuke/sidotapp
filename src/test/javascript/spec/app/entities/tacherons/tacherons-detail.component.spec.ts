import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TacheronsDetailComponent } from 'app/entities/tacherons/tacherons-detail.component';
import { Tacherons } from 'app/shared/model/tacherons.model';

describe('Component Tests', () => {
  describe('Tacherons Management Detail Component', () => {
    let comp: TacheronsDetailComponent;
    let fixture: ComponentFixture<TacheronsDetailComponent>;
    const route = ({ data: of({ tacherons: new Tacherons(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TacheronsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TacheronsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TacheronsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tacherons on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tacherons).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
