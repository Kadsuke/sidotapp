import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PrevisionAssainissementColService } from 'app/entities/prevision-assainissement-col/prevision-assainissement-col.service';
import { IPrevisionAssainissementCol, PrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';

describe('Service Tests', () => {
  describe('PrevisionAssainissementCol Service', () => {
    let injector: TestBed;
    let service: PrevisionAssainissementColService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrevisionAssainissementCol;
    let expectedResult: IPrevisionAssainissementCol | IPrevisionAssainissementCol[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PrevisionAssainissementColService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PrevisionAssainissementCol(0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PrevisionAssainissementCol', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PrevisionAssainissementCol()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PrevisionAssainissementCol', () => {
        const returnedFromService = Object.assign(
          {
            nbStep: 1,
            nbStbv: 1,
            reseaux: 1,
            nbRaccordement: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PrevisionAssainissementCol', () => {
        const returnedFromService = Object.assign(
          {
            nbStep: 1,
            nbStbv: 1,
            reseaux: 1,
            nbRaccordement: 1,
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

      it('should delete a PrevisionAssainissementCol', () => {
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
