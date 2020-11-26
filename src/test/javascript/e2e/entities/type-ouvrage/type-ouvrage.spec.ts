import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypeOuvrageComponentsPage, TypeOuvrageDeleteDialog, TypeOuvrageUpdatePage } from './type-ouvrage.page-object';

const expect = chai.expect;

describe('TypeOuvrage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeOuvrageComponentsPage: TypeOuvrageComponentsPage;
  let typeOuvrageUpdatePage: TypeOuvrageUpdatePage;
  let typeOuvrageDeleteDialog: TypeOuvrageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypeOuvrages', async () => {
    await navBarPage.goToEntity('type-ouvrage');
    typeOuvrageComponentsPage = new TypeOuvrageComponentsPage();
    await browser.wait(ec.visibilityOf(typeOuvrageComponentsPage.title), 5000);
    expect(await typeOuvrageComponentsPage.getTitle()).to.eq('sidotApp.typeOuvrage.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typeOuvrageComponentsPage.entities), ec.visibilityOf(typeOuvrageComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypeOuvrage page', async () => {
    await typeOuvrageComponentsPage.clickOnCreateButton();
    typeOuvrageUpdatePage = new TypeOuvrageUpdatePage();
    expect(await typeOuvrageUpdatePage.getPageTitle()).to.eq('sidotApp.typeOuvrage.home.createOrEditLabel');
    await typeOuvrageUpdatePage.cancel();
  });

  it('should create and save TypeOuvrages', async () => {
    const nbButtonsBeforeCreate = await typeOuvrageComponentsPage.countDeleteButtons();

    await typeOuvrageComponentsPage.clickOnCreateButton();

    await promise.all([
      typeOuvrageUpdatePage.setLibelleInput('libelle'),
      typeOuvrageUpdatePage.categorieressourcesSelectLastOption(),
      typeOuvrageUpdatePage.caracteristiqueouvrageSelectLastOption(),
    ]);

    expect(await typeOuvrageUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await typeOuvrageUpdatePage.save();
    expect(await typeOuvrageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typeOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TypeOuvrage', async () => {
    const nbButtonsBeforeDelete = await typeOuvrageComponentsPage.countDeleteButtons();
    await typeOuvrageComponentsPage.clickOnLastDeleteButton();

    typeOuvrageDeleteDialog = new TypeOuvrageDeleteDialog();
    expect(await typeOuvrageDeleteDialog.getDialogTitle()).to.eq('sidotApp.typeOuvrage.delete.question');
    await typeOuvrageDeleteDialog.clickOnConfirmButton();

    expect(await typeOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
