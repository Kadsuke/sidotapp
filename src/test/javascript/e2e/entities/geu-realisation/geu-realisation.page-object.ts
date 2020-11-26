import { element, by, ElementFinder } from 'protractor';

export class GeuRealisationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-realisation div table .btn-danger'));
  title = element.all(by.css('jhi-geu-realisation div h2#page-heading span')).first();
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

export class GeuRealisationUpdatePage {
  pageTitle = element(by.id('jhi-geu-realisation-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nbRealisationInput = element(by.id('field_nbRealisation'));

  geuaaouvrageSelect = element(by.id('field_geuaaouvrage'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNbRealisationInput(nbRealisation: string): Promise<void> {
    await this.nbRealisationInput.sendKeys(nbRealisation);
  }

  async getNbRealisationInput(): Promise<string> {
    return await this.nbRealisationInput.getAttribute('value');
  }

  async geuaaouvrageSelectLastOption(): Promise<void> {
    await this.geuaaouvrageSelect.all(by.tagName('option')).last().click();
  }

  async geuaaouvrageSelectOption(option: string): Promise<void> {
    await this.geuaaouvrageSelect.sendKeys(option);
  }

  getGeuaaouvrageSelect(): ElementFinder {
    return this.geuaaouvrageSelect;
  }

  async getGeuaaouvrageSelectedOption(): Promise<string> {
    return await this.geuaaouvrageSelect.element(by.css('option:checked')).getText();
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

export class GeuRealisationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuRealisation-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuRealisation'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
