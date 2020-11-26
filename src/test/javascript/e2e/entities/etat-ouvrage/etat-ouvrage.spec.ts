import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EtatOuvrageComponentsPage, EtatOuvrageDeleteDialog, EtatOuvrageUpdatePage } from './etat-ouvrage.page-object';

const expect = chai.expect;

describe('EtatOuvrage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let etatOuvrageComponentsPage: EtatOuvrageComponentsPage;
  let etatOuvrageUpdatePage: EtatOuvrageUpdatePage;
  let etatOuvrageDeleteDialog: EtatOuvrageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EtatOuvrages', async () => {
    await navBarPage.goToEntity('etat-ouvrage');
    etatOuvrageComponentsPage = new EtatOuvrageComponentsPage();
    await browser.wait(ec.visibilityOf(etatOuvrageComponentsPage.title), 5000);
    expect(await etatOuvrageComponentsPage.getTitle()).to.eq('sidotApp.etatOuvrage.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(etatOuvrageComponentsPage.entities), ec.visibilityOf(etatOuvrageComponentsPage.noResult)),
      1000
    );
  });

  it('should load create EtatOuvrage page', async () => {
    await etatOuvrageComponentsPage.clickOnCreateButton();
    etatOuvrageUpdatePage = new EtatOuvrageUpdatePage();
    expect(await etatOuvrageUpdatePage.getPageTitle()).to.eq('sidotApp.etatOuvrage.home.createOrEditLabel');
    await etatOuvrageUpdatePage.cancel();
  });

  it('should create and save EtatOuvrages', async () => {
    const nbButtonsBeforeCreate = await etatOuvrageComponentsPage.countDeleteButtons();

    await etatOuvrageComponentsPage.clickOnCreateButton();

    await promise.all([etatOuvrageUpdatePage.setLibelleInput('libelle')]);

    expect(await etatOuvrageUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await etatOuvrageUpdatePage.save();
    expect(await etatOuvrageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await etatOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EtatOuvrage', async () => {
    const nbButtonsBeforeDelete = await etatOuvrageComponentsPage.countDeleteButtons();
    await etatOuvrageComponentsPage.clickOnLastDeleteButton();

    etatOuvrageDeleteDialog = new EtatOuvrageDeleteDialog();
    expect(await etatOuvrageDeleteDialog.getDialogTitle()).to.eq('sidotApp.etatOuvrage.delete.question');
    await etatOuvrageDeleteDialog.clickOnConfirmButton();

    expect(await etatOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
