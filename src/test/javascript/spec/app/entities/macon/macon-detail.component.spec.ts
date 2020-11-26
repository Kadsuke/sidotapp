import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { MaconDetailComponent } from 'app/entities/macon/macon-detail.component';
import { Macon } from 'app/shared/model/macon.model';

describe('Component Tests', () => {
  describe('Macon Management Detail Component', () => {
    let comp: MaconDetailComponent;
    let fixture: ComponentFixture<MaconDetailComponent>;
    const route = ({ data: of({ macon: new Macon(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [MaconDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MaconDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MaconDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load macon on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.macon).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
