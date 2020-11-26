import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { GeuPrevisionSTBVService } from 'app/entities/geu-prevision-stbv/geu-prevision-stbv.service';
import { IGeuPrevisionSTBV, GeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

describe('Service Tests', () => {
  describe('GeuPrevisionSTBV Service', () => {
    let injector: TestBed;
    let service: GeuPrevisionSTBVService;
    let httpMock: HttpTestingController;
    let elemDefault: IGeuPrevisionSTBV;
    let expectedResult: IGeuPrevisionSTBV | IGeuPrevisionSTBV[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GeuPrevisionSTBVService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new GeuPrevisionSTBV(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GeuPrevisionSTBV', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new GeuPrevisionSTBV()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GeuPrevisionSTBV', () => {
        const returnedFromService = Object.assign(
          {
            nbCamions: 'BBBBBB',
            volume: 'BBBBBB',
            energie: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GeuPrevisionSTBV', () => {
        const returnedFromService = Object.assign(
          {
            nbCamions: 'BBBBBB',
            volume: 'BBBBBB',
            energie: 'BBBBBB',
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

      it('should delete a GeuPrevisionSTBV', () => {
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
