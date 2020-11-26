import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProduitChimiqueComponentsPage, ProduitChimiqueDeleteDialog, ProduitChimiqueUpdatePage } from './produit-chimique.page-object';

const expect = chai.expect;

describe('ProduitChimique e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let produitChimiqueComponentsPage: ProduitChimiqueComponentsPage;
  let produitChimiqueUpdatePage: ProduitChimiqueUpdatePage;
  let produitChimiqueDeleteDialog: ProduitChimiqueDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProduitChimiques', async () => {
    await navBarPage.goToEntity('produit-chimique');
    produitChimiqueComponentsPage = new ProduitChimiqueComponentsPage();
    await browser.wait(ec.visibilityOf(produitChimiqueComponentsPage.title), 5000);
    expect(await produitChimiqueComponentsPage.getTitle()).to.eq('sidotApp.produitChimique.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(produitChimiqueComponentsPage.entities), ec.visibilityOf(produitChimiqueComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProduitChimique page', async () => {
    await produitChimiqueComponentsPage.clickOnCreateButton();
    produitChimiqueUpdatePage = new ProduitChimiqueUpdatePage();
    expect(await produitChimiqueUpdatePage.getPageTitle()).to.eq('sidotApp.produitChimique.home.createOrEditLabel');
    await produitChimiqueUpdatePage.cancel();
  });

  it('should create and save ProduitChimiques', async () => {
    const nbButtonsBeforeCreate = await produitChimiqueComponentsPage.countDeleteButtons();

    await produitChimiqueComponentsPage.clickOnCreateButton();

    await promise.all([produitChimiqueUpdatePage.setLibelleInput('libelle')]);

    expect(await produitChimiqueUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await produitChimiqueUpdatePage.save();
    expect(await produitChimiqueUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await produitChimiqueComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProduitChimique', async () => {
    const nbButtonsBeforeDelete = await produitChimiqueComponentsPage.countDeleteButtons();
    await produitChimiqueComponentsPage.clickOnLastDeleteButton();

    produitChimiqueDeleteDialog = new ProduitChimiqueDeleteDialog();
    expect(await produitChimiqueDeleteDialog.getDialogTitle()).to.eq('sidotApp.produitChimique.delete.question');
    await produitChimiqueDeleteDialog.clickOnConfirmButton();

    expect(await produitChimiqueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
