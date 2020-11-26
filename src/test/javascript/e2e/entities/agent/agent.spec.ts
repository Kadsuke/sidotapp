import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AgentComponentsPage, AgentDeleteDialog, AgentUpdatePage } from './agent.page-object';

const expect = chai.expect;

describe('Agent e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let agentComponentsPage: AgentComponentsPage;
  let agentUpdatePage: AgentUpdatePage;
  let agentDeleteDialog: AgentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Agents', async () => {
    await navBarPage.goToEntity('agent');
    agentComponentsPage = new AgentComponentsPage();
    await browser.wait(ec.visibilityOf(agentComponentsPage.title), 5000);
    expect(await agentComponentsPage.getTitle()).to.eq('sidotApp.agent.home.title');
    await browser.wait(ec.or(ec.visibilityOf(agentComponentsPage.entities), ec.visibilityOf(agentComponentsPage.noResult)), 1000);
  });

  it('should load create Agent page', async () => {
    await agentComponentsPage.clickOnCreateButton();
    agentUpdatePage = new AgentUpdatePage();
    expect(await agentUpdatePage.getPageTitle()).to.eq('sidotApp.agent.home.createOrEditLabel');
    await agentUpdatePage.cancel();
  });

  it('should create and save Agents', async () => {
    const nbButtonsBeforeCreate = await agentComponentsPage.countDeleteButtons();

    await agentComponentsPage.clickOnCreateButton();

    await promise.all([
      agentUpdatePage.setNomInput('nom'),
      agentUpdatePage.setNumeroInput('numero'),
      agentUpdatePage.setRoleInput('role'),
      agentUpdatePage.siteSelectLastOption(),
    ]);

    expect(await agentUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await agentUpdatePage.getNumeroInput()).to.eq('numero', 'Expected Numero value to be equals to numero');
    expect(await agentUpdatePage.getRoleInput()).to.eq('role', 'Expected Role value to be equals to role');

    await agentUpdatePage.save();
    expect(await agentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await agentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Agent', async () => {
    const nbButtonsBeforeDelete = await agentComponentsPage.countDeleteButtons();
    await agentComponentsPage.clickOnLastDeleteButton();

    agentDeleteDialog = new AgentDeleteDialog();
    expect(await agentDeleteDialog.getDialogTitle()).to.eq('sidotApp.agent.delete.question');
    await agentDeleteDialog.clickOnConfirmButton();

    expect(await agentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
