import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RefSousDomaineComponentsPage, RefSousDomaineDeleteDialog, RefSousDomaineUpdatePage } from './ref-sous-domaine.page-object';

const expect = chai.expect;

describe('RefSousDomaine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let refSousDomaineComponentsPage: RefSousDomaineComponentsPage;
  let refSousDomaineUpdatePage: RefSousDomaineUpdatePage;
  let refSousDomaineDeleteDialog: RefSousDomaineDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RefSousDomaines', async () => {
    await navBarPage.goToEntity('ref-sous-domaine');
    refSousDomaineComponentsPage = new RefSousDomaineComponentsPage();
    await browser.wait(ec.visibilityOf(refSousDomaineComponentsPage.title), 5000);
    expect(await refSousDomaineComponentsPage.getTitle()).to.eq('sidotApp.refSousDomaine.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(refSousDomaineComponentsPage.entities), ec.visibilityOf(refSousDomaineComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RefSousDomaine page', async () => {
    await refSousDomaineComponentsPage.clickOnCreateButton();
    refSousDomaineUpdatePage = new RefSousDomaineUpdatePage();
    expect(await refSousDomaineUpdatePage.getPageTitle()).to.eq('sidotApp.refSousDomaine.home.createOrEditLabel');
    await refSousDomaineUpdatePage.cancel();
  });

  it('should create and save RefSousDomaines', async () => {
    const nbButtonsBeforeCreate = await refSousDomaineComponentsPage.countDeleteButtons();

    await refSousDomaineComponentsPage.clickOnCreateButton();

    await promise.all([refSousDomaineUpdatePage.setLibelleInput('libelle')]);

    expect(await refSousDomaineUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await refSousDomaineUpdatePage.save();
    expect(await refSousDomaineUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await refSousDomaineComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last RefSousDomaine', async () => {
    const nbButtonsBeforeDelete = await refSousDomaineComponentsPage.countDeleteButtons();
    await refSousDomaineComponentsPage.clickOnLastDeleteButton();

    refSousDomaineDeleteDialog = new RefSousDomaineDeleteDialog();
    expect(await refSousDomaineDeleteDialog.getDialogTitle()).to.eq('sidotApp.refSousDomaine.delete.question');
    await refSousDomaineDeleteDialog.clickOnConfirmButton();

    expect(await refSousDomaineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
