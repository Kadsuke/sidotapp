import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypeAnalyseComponentsPage, TypeAnalyseDeleteDialog, TypeAnalyseUpdatePage } from './type-analyse.page-object';

const expect = chai.expect;

describe('TypeAnalyse e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeAnalyseComponentsPage: TypeAnalyseComponentsPage;
  let typeAnalyseUpdatePage: TypeAnalyseUpdatePage;
  let typeAnalyseDeleteDialog: TypeAnalyseDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypeAnalyses', async () => {
    await navBarPage.goToEntity('type-analyse');
    typeAnalyseComponentsPage = new TypeAnalyseComponentsPage();
    await browser.wait(ec.visibilityOf(typeAnalyseComponentsPage.title), 5000);
    expect(await typeAnalyseComponentsPage.getTitle()).to.eq('sidotApp.typeAnalyse.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typeAnalyseComponentsPage.entities), ec.visibilityOf(typeAnalyseComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypeAnalyse page', async () => {
    await typeAnalyseComponentsPage.clickOnCreateButton();
    typeAnalyseUpdatePage = new TypeAnalyseUpdatePage();
    expect(await typeAnalyseUpdatePage.getPageTitle()).to.eq('sidotApp.typeAnalyse.home.createOrEditLabel');
    await typeAnalyseUpdatePage.cancel();
  });

  it('should create and save TypeAnalyses', async () => {
    const nbButtonsBeforeCreate = await typeAnalyseComponentsPage.countDeleteButtons();

    await typeAnalyseComponentsPage.clickOnCreateButton();

    await promise.all([typeAnalyseUpdatePage.setLibelleInput('libelle')]);

    expect(await typeAnalyseUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await typeAnalyseUpdatePage.save();
    expect(await typeAnalyseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typeAnalyseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TypeAnalyse', async () => {
    const nbButtonsBeforeDelete = await typeAnalyseComponentsPage.countDeleteButtons();
    await typeAnalyseComponentsPage.clickOnLastDeleteButton();

    typeAnalyseDeleteDialog = new TypeAnalyseDeleteDialog();
    expect(await typeAnalyseDeleteDialog.getDialogTitle()).to.eq('sidotApp.typeAnalyse.delete.question');
    await typeAnalyseDeleteDialog.clickOnConfirmButton();

    expect(await typeAnalyseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
