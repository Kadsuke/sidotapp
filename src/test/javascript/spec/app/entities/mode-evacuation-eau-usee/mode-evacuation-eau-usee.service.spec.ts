import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ModeEvacuationEauUseeService } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee.service';
import { IModeEvacuationEauUsee, ModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';

describe('Service Tests', () => {
  describe('ModeEvacuationEauUsee Service', () => {
    let injector: TestBed;
    let service: ModeEvacuationEauUseeService;
    let httpMock: HttpTestingController;
    let elemDefault: IModeEvacuationEauUsee;
    let expectedResult: IModeEvacuationEauUsee | IModeEvacuationEauUsee[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ModeEvacuationEauUseeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ModeEvacuationEauUsee(0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ModeEvacuationEauUsee', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ModeEvacuationEauUsee()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ModeEvacuationEauUsee', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ModeEvacuationEauUsee', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ModeEvacuationEauUsee', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
