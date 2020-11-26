import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RefAnneeComponentsPage, RefAnneeDeleteDialog, RefAnneeUpdatePage } from './ref-annee.page-object';

const expect = chai.expect;

describe('RefAnnee e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let refAnneeComponentsPage: RefAnneeComponentsPage;
  let refAnneeUpdatePage: RefAnneeUpdatePage;
  let refAnneeDeleteDialog: RefAnneeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RefAnnees', async () => {
    await navBarPage.goToEntity('ref-annee');
    refAnneeComponentsPage = new RefAnneeComponentsPage();
    await browser.wait(ec.visibilityOf(refAnneeComponentsPage.title), 5000);
    expect(await refAnneeComponentsPage.getTitle()).to.eq('sidotApp.refAnnee.home.title');
    await browser.wait(ec.or(ec.visibilityOf(refAnneeComponentsPage.entities), ec.visibilityOf(refAnneeComponentsPage.noResult)), 1000);
  });

  it('should load create RefAnnee page', async () => {
    await refAnneeComponentsPage.clickOnCreateButton();
    refAnneeUpdatePage = new RefAnneeUpdatePage();
    expect(await refAnneeUpdatePage.getPageTitle()).to.eq('sidotApp.refAnnee.home.createOrEditLabel');
    await refAnneeUpdatePage.cancel();
  });

  it('should create and save RefAnnees', async () => {
    const nbButtonsBeforeCreate = await refAnneeComponentsPage.countDeleteButtons();

    await refAnneeComponentsPage.clickOnCreateButton();

    await promise.all([refAnneeUpdatePage.setLibelleInput('libelle')]);

    expect(await refAnneeUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await refAnneeUpdatePage.save();
    expect(await refAnneeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await refAnneeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last RefAnnee', async () => {
    const nbButtonsBeforeDelete = await refAnneeComponentsPage.countDeleteButtons();
    await refAnneeComponentsPage.clickOnLastDeleteButton();

    refAnneeDeleteDialog = new RefAnneeDeleteDialog();
    expect(await refAnneeDeleteDialog.getDialogTitle()).to.eq('sidotApp.refAnnee.delete.question');
    await refAnneeDeleteDialog.clickOnConfirmButton();

    expect(await refAnneeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
