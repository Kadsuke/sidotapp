import { element, by, ElementFinder } from 'protractor';

export class GeuPSAComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-psa div table .btn-danger'));
  title = element.all(by.css('jhi-geu-psa div h2#page-heading span')).first();
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

export class GeuPSAUpdatePage {
  pageTitle = element(by.id('jhi-geu-psa-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dateElaborationInput = element(by.id('field_dateElaboration'));
  dateMiseEnOeuvreInput = element(by.id('field_dateMiseEnOeuvre'));

  geocommuneSelect = element(by.id('field_geocommune'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDateElaborationInput(dateElaboration: string): Promise<void> {
    await this.dateElaborationInput.sendKeys(dateElaboration);
  }

  async getDateElaborationInput(): Promise<string> {
    return await this.dateElaborationInput.getAttribute('value');
  }

  async setDateMiseEnOeuvreInput(dateMiseEnOeuvre: string): Promise<void> {
    await this.dateMiseEnOeuvreInput.sendKeys(dateMiseEnOeuvre);
  }

  async getDateMiseEnOeuvreInput(): Promise<string> {
    return await this.dateMiseEnOeuvreInput.getAttribute('value');
  }

  async geocommuneSelectLastOption(): Promise<void> {
    await this.geocommuneSelect.all(by.tagName('option')).last().click();
  }

  async geocommuneSelectOption(option: string): Promise<void> {
    await this.geocommuneSelect.sendKeys(option);
  }

  getGeocommuneSelect(): ElementFinder {
    return this.geocommuneSelect;
  }

  async getGeocommuneSelectedOption(): Promise<string> {
    return await this.geocommuneSelect.element(by.css('option:checked')).getText();
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

export class GeuPSADeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuPSA-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuPSA'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
