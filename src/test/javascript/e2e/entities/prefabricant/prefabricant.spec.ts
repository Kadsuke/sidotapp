import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PrefabricantComponentsPage, PrefabricantDeleteDialog, PrefabricantUpdatePage } from './prefabricant.page-object';

const expect = chai.expect;

describe('Prefabricant e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let prefabricantComponentsPage: PrefabricantComponentsPage;
  let prefabricantUpdatePage: PrefabricantUpdatePage;
  let prefabricantDeleteDialog: PrefabricantDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Prefabricants', async () => {
    await navBarPage.goToEntity('prefabricant');
    prefabricantComponentsPage = new PrefabricantComponentsPage();
    await browser.wait(ec.visibilityOf(prefabricantComponentsPage.title), 5000);
    expect(await prefabricantComponentsPage.getTitle()).to.eq('sidotApp.prefabricant.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(prefabricantComponentsPage.entities), ec.visibilityOf(prefabricantComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Prefabricant page', async () => {
    await prefabricantComponentsPage.clickOnCreateButton();
    prefabricantUpdatePage = new PrefabricantUpdatePage();
    expect(await prefabricantUpdatePage.getPageTitle()).to.eq('sidotApp.prefabricant.home.createOrEditLabel');
    await prefabricantUpdatePage.cancel();
  });

  it('should create and save Prefabricants', async () => {
    const nbButtonsBeforeCreate = await prefabricantComponentsPage.countDeleteButtons();

    await prefabricantComponentsPage.clickOnCreateButton();

    await promise.all([prefabricantUpdatePage.setLibelleInput('libelle')]);

    expect(await prefabricantUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await prefabricantUpdatePage.save();
    expect(await prefabricantUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await prefabricantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Prefabricant', async () => {
    const nbButtonsBeforeDelete = await prefabricantComponentsPage.countDeleteButtons();
    await prefabricantComponentsPage.clickOnLastDeleteButton();

    prefabricantDeleteDialog = new PrefabricantDeleteDialog();
    expect(await prefabricantDeleteDialog.getDialogTitle()).to.eq('sidotApp.prefabricant.delete.question');
    await prefabricantDeleteDialog.clickOnConfirmButton();

    expect(await prefabricantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
