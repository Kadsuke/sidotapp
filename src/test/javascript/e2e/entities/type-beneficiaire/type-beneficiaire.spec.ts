import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypeBeneficiaireComponentsPage, TypeBeneficiaireDeleteDialog, TypeBeneficiaireUpdatePage } from './type-beneficiaire.page-object';

const expect = chai.expect;

describe('TypeBeneficiaire e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeBeneficiaireComponentsPage: TypeBeneficiaireComponentsPage;
  let typeBeneficiaireUpdatePage: TypeBeneficiaireUpdatePage;
  let typeBeneficiaireDeleteDialog: TypeBeneficiaireDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypeBeneficiaires', async () => {
    await navBarPage.goToEntity('type-beneficiaire');
    typeBeneficiaireComponentsPage = new TypeBeneficiaireComponentsPage();
    await browser.wait(ec.visibilityOf(typeBeneficiaireComponentsPage.title), 5000);
    expect(await typeBeneficiaireComponentsPage.getTitle()).to.eq('sidotApp.typeBeneficiaire.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typeBeneficiaireComponentsPage.entities), ec.visibilityOf(typeBeneficiaireComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypeBeneficiaire page', async () => {
    await typeBeneficiaireComponentsPage.clickOnCreateButton();
    typeBeneficiaireUpdatePage = new TypeBeneficiaireUpdatePage();
    expect(await typeBeneficiaireUpdatePage.getPageTitle()).to.eq('sidotApp.typeBeneficiaire.home.createOrEditLabel');
    await typeBeneficiaireUpdatePage.cancel();
  });

  it('should create and save TypeBeneficiaires', async () => {
    const nbButtonsBeforeCreate = await typeBeneficiaireComponentsPage.countDeleteButtons();

    await typeBeneficiaireComponentsPage.clickOnCreateButton();

    await promise.all([typeBeneficiaireUpdatePage.setLibelleInput('libelle')]);

    expect(await typeBeneficiaireUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await typeBeneficiaireUpdatePage.save();
    expect(await typeBeneficiaireUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typeBeneficiaireComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TypeBeneficiaire', async () => {
    const nbButtonsBeforeDelete = await typeBeneficiaireComponentsPage.countDeleteButtons();
    await typeBeneficiaireComponentsPage.clickOnLastDeleteButton();

    typeBeneficiaireDeleteDialog = new TypeBeneficiaireDeleteDialog();
    expect(await typeBeneficiaireDeleteDialog.getDialogTitle()).to.eq('sidotApp.typeBeneficiaire.delete.question');
    await typeBeneficiaireDeleteDialog.clickOnConfirmButton();

    expect(await typeBeneficiaireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
