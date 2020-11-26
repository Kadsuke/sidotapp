import { element, by, ElementFinder } from 'protractor';

export class GeuSTBVComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-stbv div table .btn-danger'));
  title = element.all(by.css('jhi-geu-stbv div h2#page-heading span')).first();
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

export class GeuSTBVUpdatePage {
  pageTitle = element(by.id('jhi-geu-stbv-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelStbvInput = element(by.id('field_libelStbv'));
  responsableInput = element(by.id('field_responsable'));
  contactInput = element(by.id('field_contact'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelStbvInput(libelStbv: string): Promise<void> {
    await this.libelStbvInput.sendKeys(libelStbv);
  }

  async getLibelStbvInput(): Promise<string> {
    return await this.libelStbvInput.getAttribute('value');
  }

  async setResponsableInput(responsable: string): Promise<void> {
    await this.responsableInput.sendKeys(responsable);
  }

  async getResponsableInput(): Promise<string> {
    return await this.responsableInput.getAttribute('value');
  }

  async setContactInput(contact: string): Promise<void> {
    await this.contactInput.sendKeys(contact);
  }

  async getContactInput(): Promise<string> {
    return await this.contactInput.getAttribute('value');
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

export class GeuSTBVDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuSTBV-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuSTBV'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
