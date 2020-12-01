import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  PrevisionAssainissementColComponentsPage,
  PrevisionAssainissementColDeleteDialog,
  PrevisionAssainissementColUpdatePage,
} from './prevision-assainissement-col.page-object';

const expect = chai.expect;

describe('PrevisionAssainissementCol e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let previsionAssainissementColComponentsPage: PrevisionAssainissementColComponentsPage;
  let previsionAssainissementColUpdatePage: PrevisionAssainissementColUpdatePage;
  let previsionAssainissementColDeleteDialog: PrevisionAssainissementColDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PrevisionAssainissementCols', async () => {
    await navBarPage.goToEntity('prevision-assainissement-col');
    previsionAssainissementColComponentsPage = new PrevisionAssainissementColComponentsPage();
    await browser.wait(ec.visibilityOf(previsionAssainissementColComponentsPage.title), 5000);
    expect(await previsionAssainissementColComponentsPage.getTitle()).to.eq('sidotApp.previsionAssainissementCol.home.title');
    await browser.wait(
      ec.or(
        ec.visibilityOf(previsionAssainissementColComponentsPage.entities),
        ec.visibilityOf(previsionAssainissementColComponentsPage.noResult)
      ),
      1000
    );
  });

  it('should load create PrevisionAssainissementCol page', async () => {
    await previsionAssainissementColComponentsPage.clickOnCreateButton();
    previsionAssainissementColUpdatePage = new PrevisionAssainissementColUpdatePage();
    expect(await previsionAssainissementColUpdatePage.getPageTitle()).to.eq('sidotApp.previsionAssainissementCol.home.createOrEditLabel');
    await previsionAssainissementColUpdatePage.cancel();
  });

  it('should create and save PrevisionAssainissementCols', async () => {
    const nbButtonsBeforeCreate = await previsionAssainissementColComponentsPage.countDeleteButtons();

    await previsionAssainissementColComponentsPage.clickOnCreateButton();

    await promise.all([
      previsionAssainissementColUpdatePage.setNbStepInput('5'),
      previsionAssainissementColUpdatePage.setNbStbvInput('5'),
      previsionAssainissementColUpdatePage.setReseauxInput('5'),
      previsionAssainissementColUpdatePage.setNbRaccordementInput('5'),
      previsionAssainissementColUpdatePage.refanneeSelectLastOption(),
      previsionAssainissementColUpdatePage.centreSelectLastOption(),
    ]);

    expect(await previsionAssainissementColUpdatePage.getNbStepInput()).to.eq('5', 'Expected nbStep value to be equals to 5');
    expect(await previsionAssainissementColUpdatePage.getNbStbvInput()).to.eq('5', 'Expected nbStbv value to be equals to 5');
    expect(await previsionAssainissementColUpdatePage.getReseauxInput()).to.eq('5', 'Expected reseaux value to be equals to 5');
    expect(await previsionAssainissementColUpdatePage.getNbRaccordementInput()).to.eq(
      '5',
      'Expected nbRaccordement value to be equals to 5'
    );

    await previsionAssainissementColUpdatePage.save();
    expect(await previsionAssainissementColUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await previsionAssainissementColComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last PrevisionAssainissementCol', async () => {
    const nbButtonsBeforeDelete = await previsionAssainissementColComponentsPage.countDeleteButtons();
    await previsionAssainissementColComponentsPage.clickOnLastDeleteButton();

    previsionAssainissementColDeleteDialog = new PrevisionAssainissementColDeleteDialog();
    expect(await previsionAssainissementColDeleteDialog.getDialogTitle()).to.eq('sidotApp.previsionAssainissementCol.delete.question');
    await previsionAssainissementColDeleteDialog.clickOnConfirmButton();

    expect(await previsionAssainissementColComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
