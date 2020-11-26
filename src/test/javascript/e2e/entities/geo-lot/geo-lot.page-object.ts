import { element, by, ElementFinder } from 'protractor';

export class GeoLotComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geo-lot div table .btn-danger'));
  title = element.all(by.css('jhi-geo-lot div h2#page-heading span')).first();
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

export class GeoLotUpdatePage {
  pageTitle = element(by.id('jhi-geo-lot-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  geosectionSelect = element(by.id('field_geosection'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async geosectionSelectLastOption(): Promise<void> {
    await this.geosectionSelect.all(by.tagName('option')).last().click();
  }

  async geosectionSelectOption(option: string): Promise<void> {
    await this.geosectionSelect.sendKeys(option);
  }

  getGeosectionSelect(): ElementFinder {
    return this.geosectionSelect;
  }

  async getGeosectionSelectedOption(): Promise<string> {
    return await this.geosectionSelect.element(by.css('option:checked')).getText();
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

export class GeoLotDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geoLot-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geoLot'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
