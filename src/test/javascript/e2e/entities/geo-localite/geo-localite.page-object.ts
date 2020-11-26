import { element, by, ElementFinder } from 'protractor';

export class GeoLocaliteComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geo-localite div table .btn-danger'));
  title = element.all(by.css('jhi-geo-localite div h2#page-heading span')).first();
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

export class GeoLocaliteUpdatePage {
  pageTitle = element(by.id('jhi-geo-localite-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  geocommuneSelect = element(by.id('field_geocommune'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
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

export class GeoLocaliteDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geoLocalite-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geoLocalite'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
