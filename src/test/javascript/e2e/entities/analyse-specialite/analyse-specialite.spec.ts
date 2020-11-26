import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  AnalyseSpecialiteComponentsPage,
  AnalyseSpecialiteDeleteDialog,
  AnalyseSpecialiteUpdatePage,
} from './analyse-specialite.page-object';

const expect = chai.expect;

describe('AnalyseSpecialite e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let analyseSpecialiteComponentsPage: AnalyseSpecialiteComponentsPage;
  let analyseSpecialiteUpdatePage: AnalyseSpecialiteUpdatePage;
  let analyseSpecialiteDeleteDialog: AnalyseSpecialiteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AnalyseSpecialites', async () => {
    await navBarPage.goToEntity('analyse-specialite');
    analyseSpecialiteComponentsPage = new AnalyseSpecialiteComponentsPage();
    await browser.wait(ec.visibilityOf(analyseSpecialiteComponentsPage.title), 5000);
    expect(await analyseSpecialiteComponentsPage.getTitle()).to.eq('sidotApp.analyseSpecialite.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(analyseSpecialiteComponentsPage.entities), ec.visibilityOf(analyseSpecialiteComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AnalyseSpecialite page', async () => {
    await analyseSpecialiteComponentsPage.clickOnCreateButton();
    analyseSpecialiteUpdatePage = new AnalyseSpecialiteUpdatePage();
    expect(await analyseSpecialiteUpdatePage.getPageTitle()).to.eq('sidotApp.analyseSpecialite.home.createOrEditLabel');
    await analyseSpecialiteUpdatePage.cancel();
  });

  it('should create and save AnalyseSpecialites', async () => {
    const nbButtonsBeforeCreate = await analyseSpecialiteComponentsPage.countDeleteButtons();

    await analyseSpecialiteComponentsPage.clickOnCreateButton();

    await promise.all([analyseSpecialiteUpdatePage.setLibelleInput('libelle')]);

    expect(await analyseSpecialiteUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await analyseSpecialiteUpdatePage.save();
    expect(await analyseSpecialiteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await analyseSpecialiteComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last AnalyseSpecialite', async () => {
    const nbButtonsBeforeDelete = await analyseSpecialiteComponentsPage.countDeleteButtons();
    await analyseSpecialiteComponentsPage.clickOnLastDeleteButton();

    analyseSpecialiteDeleteDialog = new AnalyseSpecialiteDeleteDialog();
    expect(await analyseSpecialiteDeleteDialog.getDialogTitle()).to.eq('sidotApp.analyseSpecialite.delete.question');
    await analyseSpecialiteDeleteDialog.clickOnConfirmButton();

    expect(await analyseSpecialiteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
