import { element, by, ElementFinder } from 'protractor';

export class AgentComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-agent div table .btn-danger'));
  title = element.all(by.css('jhi-agent div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class AgentUpdatePage {
  pageTitle = element(by.id('jhi-agent-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomInput = element(by.id('field_nom'));
  numeroInput = element(by.id('field_numero'));
  roleInput = element(by.id('field_role'));

  siteSelect = element(by.id('field_site'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
  }

  async setNumeroInput(numero: string): Promise<void> {
    await this.numeroInput.sendKeys(numero);
  }

  async getNumeroInput(): Promise<string> {
    return await this.numeroInput.getAttribute('value');
  }

  async setRoleInput(role: string): Promise<void> {
    await this.roleInput.sendKeys(role);
  }

  async getRoleInput(): Promise<string> {
    return await this.roleInput.getAttribute('value');
  }

  async siteSelectLastOption(): Promise<void> {
    await this.siteSelect.all(by.tagName('option')).last().click();
  }

  async siteSelectOption(option: string): Promise<void> {
    await this.siteSelect.sendKeys(option);
  }

  getSiteSelect(): ElementFinder {
    return this.siteSelect;
  }

  async getSiteSelectedOption(): Promise<string> {
    return await this.siteSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class AgentDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-agent-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-agent'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
