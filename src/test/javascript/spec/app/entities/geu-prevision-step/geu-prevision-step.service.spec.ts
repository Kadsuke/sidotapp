import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GeuPrevisionSTEPService } from 'app/entities/geu-prevision-step/geu-prevision-step.service';
import { IGeuPrevisionSTEP, GeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';

describe('Service Tests', () => {
  describe('GeuPrevisionSTEP Service', () => {
    let injector: TestBed;
    let service: GeuPrevisionSTEPService;
    let httpMock: HttpTestingController;
    let elemDefault: IGeuPrevisionSTEP;
    let expectedResult: IGeuPrevisionSTEP | IGeuPrevisionSTEP[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GeuPrevisionSTEPService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GeuPrevisionSTEP(0, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            datePrevStep: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GeuPrevisionSTEP', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            datePrevStep: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datePrevStep: currentDate,
          },
          returnedFromService
        );

        service.create(new GeuPrevisionSTEP()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GeuPrevisionSTEP', () => {
        const returnedFromService = Object.assign(
          {
            datePrevStep: currentDate.format(DATE_TIME_FORMAT),
            volumePrevStep: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datePrevStep: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GeuPrevisionSTEP', () => {
        const returnedFromService = Object.assign(
          {
            datePrevStep: currentDate.format(DATE_TIME_FORMAT),
            volumePrevStep: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datePrevStep: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GeuPrevisionSTEP', () => {
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
