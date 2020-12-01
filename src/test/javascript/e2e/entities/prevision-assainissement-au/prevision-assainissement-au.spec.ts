import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  PrevisionAssainissementAuComponentsPage,
  PrevisionAssainissementAuDeleteDialog,
  PrevisionAssainissementAuUpdatePage,
} from './prevision-assainissement-au.page-object';

const expect = chai.expect;

describe('PrevisionAssainissementAu e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let previsionAssainissementAuComponentsPage: PrevisionAssainissementAuComponentsPage;
  let previsionAssainissementAuUpdatePage: PrevisionAssainissementAuUpdatePage;
  let previsionAssainissementAuDeleteDialog: PrevisionAssainissementAuDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PrevisionAssainissementAus', async () => {
    await navBarPage.goToEntity('prevision-assainissement-au');
    previsionAssainissementAuComponentsPage = new PrevisionAssainissementAuComponentsPage();
    await browser.wait(ec.visibilityOf(previsionAssainissementAuComponentsPage.title), 5000);
    expect(await previsionAssainissementAuComponentsPage.getTitle()).to.eq('sidotApp.previsionAssainissementAu.home.title');
    await browser.wait(
      ec.or(
        ec.visibilityOf(previsionAssainissementAuComponentsPage.entities),
        ec.visibilityOf(previsionAssainissementAuComponentsPage.noResult)
      ),
      1000
    );
  });

  it('should load create PrevisionAssainissementAu page', async () => {
    await previsionAssainissementAuComponentsPage.clickOnCreateButton();
    previsionAssainissementAuUpdatePage = new PrevisionAssainissementAuUpdatePage();
    expect(await previsionAssainissementAuUpdatePage.getPageTitle()).to.eq('sidotApp.previsionAssainissementAu.home.createOrEditLabel');
    await previsionAssainissementAuUpdatePage.cancel();
  });

  it('should create and save PrevisionAssainissementAus', async () => {
    const nbButtonsBeforeCreate = await previsionAssainissementAuComponentsPage.countDeleteButtons();

    await previsionAssainissementAuComponentsPage.clickOnCreateButton();

    await promise.all([
      previsionAssainissementAuUpdatePage.setNbLatrineInput('5'),
      previsionAssainissementAuUpdatePage.setNbPuisardInput('5'),
      previsionAssainissementAuUpdatePage.setNbPublicInput('5'),
      previsionAssainissementAuUpdatePage.setNbScolaireInput('5'),
      previsionAssainissementAuUpdatePage.setCentreDeSanteInput('5'),
      previsionAssainissementAuUpdatePage.setPopulationInput('5'),
      previsionAssainissementAuUpdatePage.refanneeSelectLastOption(),
      previsionAssainissementAuUpdatePage.centreSelectLastOption(),
    ]);

    expect(await previsionAssainissementAuUpdatePage.getNbLatrineInput()).to.eq('5', 'Expected nbLatrine value to be equals to 5');
    expect(await previsionAssainissementAuUpdatePage.getNbPuisardInput()).to.eq('5', 'Expected nbPuisard value to be equals to 5');
    expect(await previsionAssainissementAuUpdatePage.getNbPublicInput()).to.eq('5', 'Expected nbPublic value to be equals to 5');
    expect(await previsionAssainissementAuUpdatePage.getNbScolaireInput()).to.eq('5', 'Expected nbScolaire value to be equals to 5');
    expect(await previsionAssainissementAuUpdatePage.getCentreDeSanteInput()).to.eq('5', 'Expected centreDeSante value to be equals to 5');
    expect(await previsionAssainissementAuUpdatePage.getPopulationInput()).to.eq('5', 'Expected population value to be equals to 5');

    await previsionAssainissementAuUpdatePage.save();
    expect(await previsionAssainissementAuUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await previsionAssainissementAuComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last PrevisionAssainissementAu', async () => {
    const nbButtonsBeforeDelete = await previsionAssainissementAuComponentsPage.countDeleteButtons();
    await previsionAssainissementAuComponentsPage.clickOnLastDeleteButton();

    previsionAssainissementAuDeleteDialog = new PrevisionAssainissementAuDeleteDialog();
    expect(await previsionAssainissementAuDeleteDialog.getDialogTitle()).to.eq('sidotApp.previsionAssainissementAu.delete.question');
    await previsionAssainissementAuDeleteDialog.clickOnConfirmButton();

    expect(await previsionAssainissementAuComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
