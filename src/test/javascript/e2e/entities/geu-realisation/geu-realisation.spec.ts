import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuRealisationComponentsPage, GeuRealisationDeleteDialog, GeuRealisationUpdatePage } from './geu-realisation.page-object';

const expect = chai.expect;

describe('GeuRealisation e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuRealisationComponentsPage: GeuRealisationComponentsPage;
  let geuRealisationUpdatePage: GeuRealisationUpdatePage;
  let geuRealisationDeleteDialog: GeuRealisationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuRealisations', async () => {
    await navBarPage.goToEntity('geu-realisation');
    geuRealisationComponentsPage = new GeuRealisationComponentsPage();
    await browser.wait(ec.visibilityOf(geuRealisationComponentsPage.title), 5000);
    expect(await geuRealisationComponentsPage.getTitle()).to.eq('sidotApp.geuRealisation.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geuRealisationComponentsPage.entities), ec.visibilityOf(geuRealisationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeuRealisation page', async () => {
    await geuRealisationComponentsPage.clickOnCreateButton();
    geuRealisationUpdatePage = new GeuRealisationUpdatePage();
    expect(await geuRealisationUpdatePage.getPageTitle()).to.eq('sidotApp.geuRealisation.home.createOrEditLabel');
    await geuRealisationUpdatePage.cancel();
  });

  it('should create and save GeuRealisations', async () => {
    const nbButtonsBeforeCreate = await geuRealisationComponentsPage.countDeleteButtons();

    await geuRealisationComponentsPage.clickOnCreateButton();

    await promise.all([geuRealisationUpdatePage.setNbRealisationInput('5'), geuRealisationUpdatePage.geuaaouvrageSelectLastOption()]);

    expect(await geuRealisationUpdatePage.getNbRealisationInput()).to.eq('5', 'Expected nbRealisation value to be equals to 5');

    await geuRealisationUpdatePage.save();
    expect(await geuRealisationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuRealisationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GeuRealisation', async () => {
    const nbButtonsBeforeDelete = await geuRealisationComponentsPage.countDeleteButtons();
    await geuRealisationComponentsPage.clickOnLastDeleteButton();

    geuRealisationDeleteDialog = new GeuRealisationDeleteDialog();
    expect(await geuRealisationDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuRealisation.delete.question');
    await geuRealisationDeleteDialog.clickOnConfirmButton();

    expect(await geuRealisationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
