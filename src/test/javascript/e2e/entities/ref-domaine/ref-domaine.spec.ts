import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RefDomaineComponentsPage, RefDomaineDeleteDialog, RefDomaineUpdatePage } from './ref-domaine.page-object';

const expect = chai.expect;

describe('RefDomaine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let refDomaineComponentsPage: RefDomaineComponentsPage;
  let refDomaineUpdatePage: RefDomaineUpdatePage;
  let refDomaineDeleteDialog: RefDomaineDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RefDomaines', async () => {
    await navBarPage.goToEntity('ref-domaine');
    refDomaineComponentsPage = new RefDomaineComponentsPage();
    await browser.wait(ec.visibilityOf(refDomaineComponentsPage.title), 5000);
    expect(await refDomaineComponentsPage.getTitle()).to.eq('sidotApp.refDomaine.home.title');
    await browser.wait(ec.or(ec.visibilityOf(refDomaineComponentsPage.entities), ec.visibilityOf(refDomaineComponentsPage.noResult)), 1000);
  });

  it('should load create RefDomaine page', async () => {
    await refDomaineComponentsPage.clickOnCreateButton();
    refDomaineUpdatePage = new RefDomaineUpdatePage();
    expect(await refDomaineUpdatePage.getPageTitle()).to.eq('sidotApp.refDomaine.home.createOrEditLabel');
    await refDomaineUpdatePage.cancel();
  });

  it('should create and save RefDomaines', async () => {
    const nbButtonsBeforeCreate = await refDomaineComponentsPage.countDeleteButtons();

    await refDomaineComponentsPage.clickOnCreateButton();

    await promise.all([refDomaineUpdatePage.setLibelleInput('libelle')]);

    expect(await refDomaineUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await refDomaineUpdatePage.save();
    expect(await refDomaineUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await refDomaineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last RefDomaine', async () => {
    const nbButtonsBeforeDelete = await refDomaineComponentsPage.countDeleteButtons();
    await refDomaineComponentsPage.clickOnLastDeleteButton();

    refDomaineDeleteDialog = new RefDomaineDeleteDialog();
    expect(await refDomaineDeleteDialog.getDialogTitle()).to.eq('sidotApp.refDomaine.delete.question');
    await refDomaineDeleteDialog.clickOnConfirmButton();

    expect(await refDomaineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
