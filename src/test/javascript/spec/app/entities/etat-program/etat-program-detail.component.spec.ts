import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatProgramDetailComponent } from 'app/entities/etat-program/etat-program-detail.component';
import { EtatProgram } from 'app/shared/model/etat-program.model';

describe('Component Tests', () => {
  describe('EtatProgram Management Detail Component', () => {
    let comp: EtatProgramDetailComponent;
    let fixture: ComponentFixture<EtatProgramDetailComponent>;
    const route = ({ data: of({ etatProgram: new EtatProgram(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatProgramDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EtatProgramDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EtatProgramDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load etatProgram on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.etatProgram).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
