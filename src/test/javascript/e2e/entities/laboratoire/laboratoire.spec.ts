import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LaboratoireComponentsPage, LaboratoireDeleteDialog, LaboratoireUpdatePage } from './laboratoire.page-object';

const expect = chai.expect;

describe('Laboratoire e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let laboratoireComponentsPage: LaboratoireComponentsPage;
  let laboratoireUpdatePage: LaboratoireUpdatePage;
  let laboratoireDeleteDialog: LaboratoireDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Laboratoires', async () => {
    await navBarPage.goToEntity('laboratoire');
    laboratoireComponentsPage = new LaboratoireComponentsPage();
    await browser.wait(ec.visibilityOf(laboratoireComponentsPage.title), 5000);
    expect(await laboratoireComponentsPage.getTitle()).to.eq('sidotApp.laboratoire.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(laboratoireComponentsPage.entities), ec.visibilityOf(laboratoireComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Laboratoire page', async () => {
    await laboratoireComponentsPage.clickOnCreateButton();
    laboratoireUpdatePage = new LaboratoireUpdatePage();
    expect(await laboratoireUpdatePage.getPageTitle()).to.eq('sidotApp.laboratoire.home.createOrEditLabel');
    await laboratoireUpdatePage.cancel();
  });

  it('should create and save Laboratoires', async () => {
    const nbButtonsBeforeCreate = await laboratoireComponentsPage.countDeleteButtons();

    await laboratoireComponentsPage.clickOnCreateButton();

    await promise.all([laboratoireUpdatePage.setLibelleInput('libelle')]);

    expect(await laboratoireUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await laboratoireUpdatePage.save();
    expect(await laboratoireUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await laboratoireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Laboratoire', async () => {
    const nbButtonsBeforeDelete = await laboratoireComponentsPage.countDeleteButtons();
    await laboratoireComponentsPage.clickOnLastDeleteButton();

    laboratoireDeleteDialog = new LaboratoireDeleteDialog();
    expect(await laboratoireDeleteDialog.getDialogTitle()).to.eq('sidotApp.laboratoire.delete.question');
    await laboratoireDeleteDialog.clickOnConfirmButton();

    expect(await laboratoireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
