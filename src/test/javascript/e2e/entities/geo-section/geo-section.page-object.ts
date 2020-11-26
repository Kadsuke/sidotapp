import { element, by, ElementFinder } from 'protractor';

export class GeoSectionComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geo-section div table .btn-danger'));
  title = element.all(by.css('jhi-geo-section div h2#page-heading span')).first();
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

export class GeoSectionUpdatePage {
  pageTitle = element(by.id('jhi-geo-section-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  geosecteurSelect = element(by.id('field_geosecteur'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async geosecteurSelectLastOption(): Promise<void> {
    await this.geosecteurSelect.all(by.tagName('option')).last().click();
  }

  async geosecteurSelectOption(option: string): Promise<void> {
    await this.geosecteurSelect.sendKeys(option);
  }

  getGeosecteurSelect(): ElementFinder {
    return this.geosecteurSelect;
  }

  async getGeosecteurSelectedOption(): Promise<string> {
    return await this.geosecteurSelect.element(by.css('option:checked')).getText();
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

export class GeoSectionDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geoSection-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geoSection'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
