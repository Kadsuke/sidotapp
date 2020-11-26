import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RefMoisComponentsPage, RefMoisDeleteDialog, RefMoisUpdatePage } from './ref-mois.page-object';

const expect = chai.expect;

describe('RefMois e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let refMoisComponentsPage: RefMoisComponentsPage;
  let refMoisUpdatePage: RefMoisUpdatePage;
  let refMoisDeleteDialog: RefMoisDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RefMois', async () => {
    await navBarPage.goToEntity('ref-mois');
    refMoisComponentsPage = new RefMoisComponentsPage();
    await browser.wait(ec.visibilityOf(refMoisComponentsPage.title), 5000);
    expect(await refMoisComponentsPage.getTitle()).to.eq('sidotApp.refMois.home.title');
    await browser.wait(ec.or(ec.visibilityOf(refMoisComponentsPage.entities), ec.visibilityOf(refMoisComponentsPage.noResult)), 1000);
  });

  it('should load create RefMois page', async () => {
    await refMoisComponentsPage.clickOnCreateButton();
    refMoisUpdatePage = new RefMoisUpdatePage();
    expect(await refMoisUpdatePage.getPageTitle()).to.eq('sidotApp.refMois.home.createOrEditLabel');
    await refMoisUpdatePage.cancel();
  });

  it('should create and save RefMois', async () => {
    const nbButtonsBeforeCreate = await refMoisComponentsPage.countDeleteButtons();

    await refMoisComponentsPage.clickOnCreateButton();

    await promise.all([refMoisUpdatePage.setLibelleInput('libelle')]);

    expect(await refMoisUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await refMoisUpdatePage.save();
    expect(await refMoisUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await refMoisComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last RefMois', async () => {
    const nbButtonsBeforeDelete = await refMoisComponentsPage.countDeleteButtons();
    await refMoisComponentsPage.clickOnLastDeleteButton();

    refMoisDeleteDialog = new RefMoisDeleteDialog();
    expect(await refMoisDeleteDialog.getDialogTitle()).to.eq('sidotApp.refMois.delete.question');
    await refMoisDeleteDialog.clickOnConfirmButton();

    expect(await refMoisComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
