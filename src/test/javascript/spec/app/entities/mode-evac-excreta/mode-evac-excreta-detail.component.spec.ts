import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { ModeEvacExcretaDetailComponent } from 'app/entities/mode-evac-excreta/mode-evac-excreta-detail.component';
import { ModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';

describe('Component Tests', () => {
  describe('ModeEvacExcreta Management Detail Component', () => {
    let comp: ModeEvacExcretaDetailComponent;
    let fixture: ComponentFixture<ModeEvacExcretaDetailComponent>;
    const route = ({ data: of({ modeEvacExcreta: new ModeEvacExcreta(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ModeEvacExcretaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ModeEvacExcretaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModeEvacExcretaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modeEvacExcreta on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modeEvacExcreta).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
