import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CaracteristiqueOuvrageComponentsPage,
  CaracteristiqueOuvrageDeleteDialog,
  CaracteristiqueOuvrageUpdatePage,
} from './caracteristique-ouvrage.page-object';

const expect = chai.expect;

describe('CaracteristiqueOuvrage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let caracteristiqueOuvrageComponentsPage: CaracteristiqueOuvrageComponentsPage;
  let caracteristiqueOuvrageUpdatePage: CaracteristiqueOuvrageUpdatePage;
  let caracteristiqueOuvrageDeleteDialog: CaracteristiqueOuvrageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CaracteristiqueOuvrages', async () => {
    await navBarPage.goToEntity('caracteristique-ouvrage');
    caracteristiqueOuvrageComponentsPage = new CaracteristiqueOuvrageComponentsPage();
    await browser.wait(ec.visibilityOf(caracteristiqueOuvrageComponentsPage.title), 5000);
    expect(await caracteristiqueOuvrageComponentsPage.getTitle()).to.eq('sidotApp.caracteristiqueOuvrage.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(caracteristiqueOuvrageComponentsPage.entities), ec.visibilityOf(caracteristiqueOuvrageComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CaracteristiqueOuvrage page', async () => {
    await caracteristiqueOuvrageComponentsPage.clickOnCreateButton();
    caracteristiqueOuvrageUpdatePage = new CaracteristiqueOuvrageUpdatePage();
    expect(await caracteristiqueOuvrageUpdatePage.getPageTitle()).to.eq('sidotApp.caracteristiqueOuvrage.home.createOrEditLabel');
    await caracteristiqueOuvrageUpdatePage.cancel();
  });

  it('should create and save CaracteristiqueOuvrages', async () => {
    const nbButtonsBeforeCreate = await caracteristiqueOuvrageComponentsPage.countDeleteButtons();

    await caracteristiqueOuvrageComponentsPage.clickOnCreateButton();

    await promise.all([
      caracteristiqueOuvrageUpdatePage.setLibelleInput('libelle'),
      caracteristiqueOuvrageUpdatePage.setUniteInput('unite'),
      caracteristiqueOuvrageUpdatePage.typeouvrageSelectLastOption(),
    ]);

    expect(await caracteristiqueOuvrageUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');
    expect(await caracteristiqueOuvrageUpdatePage.getUniteInput()).to.eq('unite', 'Expected Unite value to be equals to unite');

    await caracteristiqueOuvrageUpdatePage.save();
    expect(await caracteristiqueOuvrageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await caracteristiqueOuvrageComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CaracteristiqueOuvrage', async () => {
    const nbButtonsBeforeDelete = await caracteristiqueOuvrageComponentsPage.countDeleteButtons();
    await caracteristiqueOuvrageComponentsPage.clickOnLastDeleteButton();

    caracteristiqueOuvrageDeleteDialog = new CaracteristiqueOuvrageDeleteDialog();
    expect(await caracteristiqueOuvrageDeleteDialog.getDialogTitle()).to.eq('sidotApp.caracteristiqueOuvrage.delete.question');
    await caracteristiqueOuvrageDeleteDialog.clickOnConfirmButton();

    expect(await caracteristiqueOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
