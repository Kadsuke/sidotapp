import { element, by, ElementFinder } from 'protractor';

export class GeoParcelleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geo-parcelle div table .btn-danger'));
  title = element.all(by.css('jhi-geo-parcelle div h2#page-heading span')).first();
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

export class GeoParcelleUpdatePage {
  pageTitle = element(by.id('jhi-geo-parcelle-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  geolotSelect = element(by.id('field_geolot'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async geolotSelectLastOption(): Promise<void> {
    await this.geolotSelect.all(by.tagName('option')).last().click();
  }

  async geolotSelectOption(option: string): Promise<void> {
    await this.geolotSelect.sendKeys(option);
  }

  getGeolotSelect(): ElementFinder {
    return this.geolotSelect;
  }

  async getGeolotSelectedOption(): Promise<string> {
    return await this.geolotSelect.element(by.css('option:checked')).getText();
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

export class GeoParcelleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geoParcelle-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geoParcelle'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
