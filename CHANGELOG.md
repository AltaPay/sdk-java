# Changelog
All notable changes to this project will be documented in this file.

## [1.0.20]

- Expose `FormTemplate` in `/getTerminals`  indicating template used to display payment form
- Expose `MethodConfiguration` in `/getTerminals` allowing to pass custom configuration needed for some of they payment methods (like PayPal)

## [1.0.19]

- Fix multi-threading issues while parsing XML documents 

## [1.0.18]

- Restore `Description` in `/getTerminals` response to be able to customize the display of payment methods (originally introduced in 1.0.15)

## [1.0.17]

- Expose `Translations` in `/getTerminals` response in order to support custom field translations per language

## [1.0.16]

- Expose `SupportedPrimaryPaymentAuthTypes` in `/getTerminals` response in order to filter out payment methods not matched with auth type from session

## [1.0.15]

- Expose `LogoUrl` and `Description` in `/getTerminals` response to be able to customize the display of payment methods

## [1.0.14]

- Add `productUrl` field to `OrderLine` to be included in /createPaymentRequest

## [1.0.13]

- Expose `ShopName` in `/getTerminals` response to be able to filter payment methods by shop name

## [1.0.12]

- Add `retentionParameter` field to `AgreementConfig`

## [1.0.11]

- Expose `SupportedAgreementTypes` in `/getTerminals` response in order filter out non-agreement payment methods if session relates to agreement payment type


## [1.0.10]

- Expose `appUrl` in `/createPaymentRequest` response in order to support app redirect for mobile requests

## [1.0.9]

- Expose `Products` in `/getTerminals` in order to support multi-product payment providers

## [1.0.8]

- Expose `CanUseCredit` and `CanIssueNewCredit` in `/getTerminals` to comply with the newest Finnish Consumer protection act changes

## [1.0.7]

- Fix for retrieving PrimaryMethod object in Terminal

## [1.0.6]

- Support for PrimaryMethod object in Terminal

## [1.0.5]

- Support API changes for getTerminals

## [1.0.4]

- Support API changes from 20230412
- Support enforced HTTP methods

## [1.0.3]

- Support API changes from 20221026
- Support new `method` list in `/getTerminals`

## [1.0.2]

- Add support for Apple Pay
- Add support for new 'Agreements Engine' parameters

## [1.0.1]

- Update format of the User-Agent header

## [1.0.0]

- Support API changes from 20210324
- Add support to agreements using agreement_type
