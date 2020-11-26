import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  GeuRealisationSTBVComponentsPage,
  GeuRealisationSTBVDeleteDialog,
  GeuRealisationSTBVUpdatePage,
} from './geu-realisation-stbv.page-object';

const expect = chai.expect;

describe('GeuRealisationSTBV e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuRealisationSTBVComponentsPage: GeuRealisationSTBVComponentsPage;
  let geuRealisationSTBVUpdatePage: GeuRealisationSTBVUpdatePage;
  let geuRealisationSTBVDeleteDialog: GeuRealisationSTBVDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuRealisationSTBVS', async () => {
    await navBarPage.goToEntity('geu-realisation-stbv');
    geuRealisationSTBVComponentsPage = new GeuRealisationSTBVComponentsPage();
    await browser.wait(ec.visibilityOf(geuRealisationSTBVComponentsPage.title), 5000);
    expect(await geuRealisationSTBVComponentsPage.getTitle()).to.eq('sidotApp.geuRealisationSTBV.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geuRealisationSTBVComponentsPage.entities), ec.visibilityOf(geuRealisationSTBVComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeuRealisationSTBV page', async () => {
    await geuRealisationSTBVComponentsPage.clickOnCreateButton();
    geuRealisationSTBVUpdatePage = new GeuRealisationSTBVUpdatePage();
    expect(await geuRealisationSTBVUpdatePage.getPageTitle()).to.eq('sidotApp.geuRealisationSTBV.home.createOrEditLabel');
    await geuRealisationSTBVUpdatePage.cancel();
  });

  it('should create and save GeuRealisationSTBVS', async () => {
    const nbButtonsBeforeCreate = await geuRealisationSTBVComponentsPage.countDeleteButtons();

    await geuRealisationSTBVComponentsPage.clickOnCreateButton();

    await promise.all([
      geuRealisationSTBVUpdatePage.setNbCamionsInput('nbCamions'),
      geuRealisationSTBVUpdatePage.setVolumeInput('volume'),
      geuRealisationSTBVUpdatePage.setEnergieInput('energie'),
      geuRealisationSTBVUpdatePage.geustbvSelectLastOption(),
    ]);

    expect(await geuRealisationSTBVUpdatePage.getNbCamionsInput()).to.eq('nbCamions', 'Expected NbCamions value to be equals to nbCamions');
    expect(await geuRealisationSTBVUpdatePage.getVolumeInput()).to.eq('volume', 'Expected Volume value to be equals to volume');
    expect(await geuRealisationSTBVUpdatePage.getEnergieInput()).to.eq('energie', 'Expected Energie value to be equals to energie');

    await geuRealisationSTBVUpdatePage.save();
    expect(await geuRealisationSTBVUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuRealisationSTBVComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GeuRealisationSTBV', async () => {
    const nbButtonsBeforeDelete = await geuRealisationSTBVComponentsPage.countDeleteButtons();
    await geuRealisationSTBVComponentsPage.clickOnLastDeleteButton();

    geuRealisationSTBVDeleteDialog = new GeuRealisationSTBVDeleteDialog();
    expect(await geuRealisationSTBVDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuRealisationSTBV.delete.question');
    await geuRealisationSTBVDeleteDialog.clickOnConfirmButton();

    expect(await geuRealisationSTBVComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
