import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PrevisionPsaComponentsPage, PrevisionPsaDeleteDialog, PrevisionPsaUpdatePage } from './prevision-psa.page-object';

const expect = chai.expect;

describe('PrevisionPsa e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let previsionPsaComponentsPage: PrevisionPsaComponentsPage;
  let previsionPsaUpdatePage: PrevisionPsaUpdatePage;
  let previsionPsaDeleteDialog: PrevisionPsaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PrevisionPsas', async () => {
    await navBarPage.goToEntity('prevision-psa');
    previsionPsaComponentsPage = new PrevisionPsaComponentsPage();
    await browser.wait(ec.visibilityOf(previsionPsaComponentsPage.title), 5000);
    expect(await previsionPsaComponentsPage.getTitle()).to.eq('sidotApp.previsionPsa.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(previsionPsaComponentsPage.entities), ec.visibilityOf(previsionPsaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create PrevisionPsa page', async () => {
    await previsionPsaComponentsPage.clickOnCreateButton();
    previsionPsaUpdatePage = new PrevisionPsaUpdatePage();
    expect(await previsionPsaUpdatePage.getPageTitle()).to.eq('sidotApp.previsionPsa.home.createOrEditLabel');
    await previsionPsaUpdatePage.cancel();
  });

  it('should create and save PrevisionPsas', async () => {
    const nbButtonsBeforeCreate = await previsionPsaComponentsPage.countDeleteButtons();

    await previsionPsaComponentsPage.clickOnCreateButton();

    await promise.all([
      previsionPsaUpdatePage.setElaboreInput('5'),
      previsionPsaUpdatePage.setMiseEnOeuvreInput('5'),
      previsionPsaUpdatePage.centreSelectLastOption(),
      previsionPsaUpdatePage.refAnneeSelectLastOption(),
    ]);

    expect(await previsionPsaUpdatePage.getElaboreInput()).to.eq('5', 'Expected elabore value to be equals to 5');
    expect(await previsionPsaUpdatePage.getMiseEnOeuvreInput()).to.eq('5', 'Expected miseEnOeuvre value to be equals to 5');

    await previsionPsaUpdatePage.save();
    expect(await previsionPsaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await previsionPsaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last PrevisionPsa', async () => {
    const nbButtonsBeforeDelete = await previsionPsaComponentsPage.countDeleteButtons();
    await previsionPsaComponentsPage.clickOnLastDeleteButton();

    previsionPsaDeleteDialog = new PrevisionPsaDeleteDialog();
    expect(await previsionPsaDeleteDialog.getDialogTitle()).to.eq('sidotApp.previsionPsa.delete.question');
    await previsionPsaDeleteDialog.clickOnConfirmButton();

    expect(await previsionPsaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
