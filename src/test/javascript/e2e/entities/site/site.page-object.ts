import { element, by, ElementFinder } from 'protractor';

export class SiteComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-site div table .btn-danger'));
  title = element.all(by.css('jhi-site div h2#page-heading span')).first();
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

export class SiteUpdatePage {
  pageTitle = element(by.id('jhi-site-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));
  responsableInput = element(by.id('field_responsable'));
  contactInput = element(by.id('field_contact'));

  centreSelect = element(by.id('field_centre'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
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

  async centreSelectLastOption(): Promise<void> {
    await this.centreSelect.all(by.tagName('option')).last().click();
  }

  async centreSelectOption(option: string): Promise<void> {
    await this.centreSelect.sendKeys(option);
  }

  getCentreSelect(): ElementFinder {
    return this.centreSelect;
  }

  async getCentreSelectedOption(): Promise<string> {
    return await this.centreSelect.element(by.css('option:checked')).getText();
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

export class SiteDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-site-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-site'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
