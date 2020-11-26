import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EtatEquipementComponentsPage, EtatEquipementDeleteDialog, EtatEquipementUpdatePage } from './etat-equipement.page-object';

const expect = chai.expect;

describe('EtatEquipement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let etatEquipementComponentsPage: EtatEquipementComponentsPage;
  let etatEquipementUpdatePage: EtatEquipementUpdatePage;
  let etatEquipementDeleteDialog: EtatEquipementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EtatEquipements', async () => {
    await navBarPage.goToEntity('etat-equipement');
    etatEquipementComponentsPage = new EtatEquipementComponentsPage();
    await browser.wait(ec.visibilityOf(etatEquipementComponentsPage.title), 5000);
    expect(await etatEquipementComponentsPage.getTitle()).to.eq('sidotApp.etatEquipement.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(etatEquipementComponentsPage.entities), ec.visibilityOf(etatEquipementComponentsPage.noResult)),
      1000
    );
  });

  it('should load create EtatEquipement page', async () => {
    await etatEquipementComponentsPage.clickOnCreateButton();
    etatEquipementUpdatePage = new EtatEquipementUpdatePage();
    expect(await etatEquipementUpdatePage.getPageTitle()).to.eq('sidotApp.etatEquipement.home.createOrEditLabel');
    await etatEquipementUpdatePage.cancel();
  });

  it('should create and save EtatEquipements', async () => {
    const nbButtonsBeforeCreate = await etatEquipementComponentsPage.countDeleteButtons();

    await etatEquipementComponentsPage.clickOnCreateButton();

    await promise.all([etatEquipementUpdatePage.setLibelleInput('libelle')]);

    expect(await etatEquipementUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await etatEquipementUpdatePage.save();
    expect(await etatEquipementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await etatEquipementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last EtatEquipement', async () => {
    const nbButtonsBeforeDelete = await etatEquipementComponentsPage.countDeleteButtons();
    await etatEquipementComponentsPage.clickOnLastDeleteButton();

    etatEquipementDeleteDialog = new EtatEquipementDeleteDialog();
    expect(await etatEquipementDeleteDialog.getDialogTitle()).to.eq('sidotApp.etatEquipement.delete.question');
    await etatEquipementDeleteDialog.clickOnConfirmButton();

    expect(await etatEquipementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
