import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypeEquipementComponentsPage, TypeEquipementDeleteDialog, TypeEquipementUpdatePage } from './type-equipement.page-object';

const expect = chai.expect;

describe('TypeEquipement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeEquipementComponentsPage: TypeEquipementComponentsPage;
  let typeEquipementUpdatePage: TypeEquipementUpdatePage;
  let typeEquipementDeleteDialog: TypeEquipementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypeEquipements', async () => {
    await navBarPage.goToEntity('type-equipement');
    typeEquipementComponentsPage = new TypeEquipementComponentsPage();
    await browser.wait(ec.visibilityOf(typeEquipementComponentsPage.title), 5000);
    expect(await typeEquipementComponentsPage.getTitle()).to.eq('sidotApp.typeEquipement.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typeEquipementComponentsPage.entities), ec.visibilityOf(typeEquipementComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypeEquipement page', async () => {
    await typeEquipementComponentsPage.clickOnCreateButton();
    typeEquipementUpdatePage = new TypeEquipementUpdatePage();
    expect(await typeEquipementUpdatePage.getPageTitle()).to.eq('sidotApp.typeEquipement.home.createOrEditLabel');
    await typeEquipementUpdatePage.cancel();
  });

  it('should create and save TypeEquipements', async () => {
    const nbButtonsBeforeCreate = await typeEquipementComponentsPage.countDeleteButtons();

    await typeEquipementComponentsPage.clickOnCreateButton();

    await promise.all([typeEquipementUpdatePage.setLibelleInput('libelle')]);

    expect(await typeEquipementUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await typeEquipementUpdatePage.save();
    expect(await typeEquipementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typeEquipementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TypeEquipement', async () => {
    const nbButtonsBeforeDelete = await typeEquipementComponentsPage.countDeleteButtons();
    await typeEquipementComponentsPage.clickOnLastDeleteButton();

    typeEquipementDeleteDialog = new TypeEquipementDeleteDialog();
    expect(await typeEquipementDeleteDialog.getDialogTitle()).to.eq('sidotApp.typeEquipement.delete.question');
    await typeEquipementDeleteDialog.clickOnConfirmButton();

    expect(await typeEquipementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
