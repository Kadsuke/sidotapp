import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuPrevisionSTBVComponentsPage, GeuPrevisionSTBVDeleteDialog, GeuPrevisionSTBVUpdatePage } from './geu-prevision-stbv.page-object';

const expect = chai.expect;

describe('GeuPrevisionSTBV e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuPrevisionSTBVComponentsPage: GeuPrevisionSTBVComponentsPage;
  let geuPrevisionSTBVUpdatePage: GeuPrevisionSTBVUpdatePage;
  let geuPrevisionSTBVDeleteDialog: GeuPrevisionSTBVDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuPrevisionSTBVS', async () => {
    await navBarPage.goToEntity('geu-prevision-stbv');
    geuPrevisionSTBVComponentsPage = new GeuPrevisionSTBVComponentsPage();
    await browser.wait(ec.visibilityOf(geuPrevisionSTBVComponentsPage.title), 5000);
    expect(await geuPrevisionSTBVComponentsPage.getTitle()).to.eq('sidotApp.geuPrevisionSTBV.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geuPrevisionSTBVComponentsPage.entities), ec.visibilityOf(geuPrevisionSTBVComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeuPrevisionSTBV page', async () => {
    await geuPrevisionSTBVComponentsPage.clickOnCreateButton();
    geuPrevisionSTBVUpdatePage = new GeuPrevisionSTBVUpdatePage();
    expect(await geuPrevisionSTBVUpdatePage.getPageTitle()).to.eq('sidotApp.geuPrevisionSTBV.home.createOrEditLabel');
    await geuPrevisionSTBVUpdatePage.cancel();
  });

  it('should create and save GeuPrevisionSTBVS', async () => {
    const nbButtonsBeforeCreate = await geuPrevisionSTBVComponentsPage.countDeleteButtons();

    await geuPrevisionSTBVComponentsPage.clickOnCreateButton();

    await promise.all([
      geuPrevisionSTBVUpdatePage.setNbCamionsInput('nbCamions'),
      geuPrevisionSTBVUpdatePage.setVolumeInput('volume'),
      geuPrevisionSTBVUpdatePage.setEnergieInput('energie'),
      geuPrevisionSTBVUpdatePage.geustbvSelectLastOption(),
    ]);

    expect(await geuPrevisionSTBVUpdatePage.getNbCamionsInput()).to.eq('nbCamions', 'Expected NbCamions value to be equals to nbCamions');
    expect(await geuPrevisionSTBVUpdatePage.getVolumeInput()).to.eq('volume', 'Expected Volume value to be equals to volume');
    expect(await geuPrevisionSTBVUpdatePage.getEnergieInput()).to.eq('energie', 'Expected Energie value to be equals to energie');

    await geuPrevisionSTBVUpdatePage.save();
    expect(await geuPrevisionSTBVUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuPrevisionSTBVComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GeuPrevisionSTBV', async () => {
    const nbButtonsBeforeDelete = await geuPrevisionSTBVComponentsPage.countDeleteButtons();
    await geuPrevisionSTBVComponentsPage.clickOnLastDeleteButton();

    geuPrevisionSTBVDeleteDialog = new GeuPrevisionSTBVDeleteDialog();
    expect(await geuPrevisionSTBVDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuPrevisionSTBV.delete.question');
    await geuPrevisionSTBVDeleteDialog.clickOnConfirmButton();

    expect(await geuPrevisionSTBVComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
