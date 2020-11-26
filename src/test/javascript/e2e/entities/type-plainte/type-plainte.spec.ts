import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypePlainteComponentsPage, TypePlainteDeleteDialog, TypePlainteUpdatePage } from './type-plainte.page-object';

const expect = chai.expect;

describe('TypePlainte e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typePlainteComponentsPage: TypePlainteComponentsPage;
  let typePlainteUpdatePage: TypePlainteUpdatePage;
  let typePlainteDeleteDialog: TypePlainteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypePlaintes', async () => {
    await navBarPage.goToEntity('type-plainte');
    typePlainteComponentsPage = new TypePlainteComponentsPage();
    await browser.wait(ec.visibilityOf(typePlainteComponentsPage.title), 5000);
    expect(await typePlainteComponentsPage.getTitle()).to.eq('sidotApp.typePlainte.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typePlainteComponentsPage.entities), ec.visibilityOf(typePlainteComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypePlainte page', async () => {
    await typePlainteComponentsPage.clickOnCreateButton();
    typePlainteUpdatePage = new TypePlainteUpdatePage();
    expect(await typePlainteUpdatePage.getPageTitle()).to.eq('sidotApp.typePlainte.home.createOrEditLabel');
    await typePlainteUpdatePage.cancel();
  });

  it('should create and save TypePlaintes', async () => {
    const nbButtonsBeforeCreate = await typePlainteComponentsPage.countDeleteButtons();

    await typePlainteComponentsPage.clickOnCreateButton();

    await promise.all([typePlainteUpdatePage.setLibelleInput('libelle')]);

    expect(await typePlainteUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await typePlainteUpdatePage.save();
    expect(await typePlainteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typePlainteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TypePlainte', async () => {
    const nbButtonsBeforeDelete = await typePlainteComponentsPage.countDeleteButtons();
    await typePlainteComponentsPage.clickOnLastDeleteButton();

    typePlainteDeleteDialog = new TypePlainteDeleteDialog();
    expect(await typePlainteDeleteDialog.getDialogTitle()).to.eq('sidotApp.typePlainte.delete.question');
    await typePlainteDeleteDialog.clickOnConfirmButton();

    expect(await typePlainteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
