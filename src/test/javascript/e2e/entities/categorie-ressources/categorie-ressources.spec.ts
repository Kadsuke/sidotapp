import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CategorieRessourcesComponentsPage,
  CategorieRessourcesDeleteDialog,
  CategorieRessourcesUpdatePage,
} from './categorie-ressources.page-object';

const expect = chai.expect;

describe('CategorieRessources e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let categorieRessourcesComponentsPage: CategorieRessourcesComponentsPage;
  let categorieRessourcesUpdatePage: CategorieRessourcesUpdatePage;
  let categorieRessourcesDeleteDialog: CategorieRessourcesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CategorieRessources', async () => {
    await navBarPage.goToEntity('categorie-ressources');
    categorieRessourcesComponentsPage = new CategorieRessourcesComponentsPage();
    await browser.wait(ec.visibilityOf(categorieRessourcesComponentsPage.title), 5000);
    expect(await categorieRessourcesComponentsPage.getTitle()).to.eq('sidotApp.categorieRessources.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(categorieRessourcesComponentsPage.entities), ec.visibilityOf(categorieRessourcesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CategorieRessources page', async () => {
    await categorieRessourcesComponentsPage.clickOnCreateButton();
    categorieRessourcesUpdatePage = new CategorieRessourcesUpdatePage();
    expect(await categorieRessourcesUpdatePage.getPageTitle()).to.eq('sidotApp.categorieRessources.home.createOrEditLabel');
    await categorieRessourcesUpdatePage.cancel();
  });

  it('should create and save CategorieRessources', async () => {
    const nbButtonsBeforeCreate = await categorieRessourcesComponentsPage.countDeleteButtons();

    await categorieRessourcesComponentsPage.clickOnCreateButton();

    await promise.all([categorieRessourcesUpdatePage.setLibelleInput('libelle')]);

    expect(await categorieRessourcesUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await categorieRessourcesUpdatePage.save();
    expect(await categorieRessourcesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await categorieRessourcesComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CategorieRessources', async () => {
    const nbButtonsBeforeDelete = await categorieRessourcesComponentsPage.countDeleteButtons();
    await categorieRessourcesComponentsPage.clickOnLastDeleteButton();

    categorieRessourcesDeleteDialog = new CategorieRessourcesDeleteDialog();
    expect(await categorieRessourcesDeleteDialog.getDialogTitle()).to.eq('sidotApp.categorieRessources.delete.question');
    await categorieRessourcesDeleteDialog.clickOnConfirmButton();

    expect(await categorieRessourcesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
