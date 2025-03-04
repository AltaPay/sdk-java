# Changelog
All notable changes to this project will be documented in this file.

## [2.0.4]

- Add `Recipient` information to `PaymentRequest`
- Add `AccountIdentifier` to `CustomerInfo`

## [2.0.3]

- Add `AuthorisationExpiryDate` to `Transaction`

## [2.0.2]

- Add `Authentication` entity to `Transaction` 

## [2.0.1]

- Update java compilation version to 17
- Migrate build from Ant to Gradle
- Use transitive dependencies instead of embedded ones in jar

## [1.0.25]

- Add `callback_ok` and `callback_fail` for subscription endpoints.

## [1.0.24]

- Add `transaction_info` for subscription endpoints
- Use new `payments` endpoint for fetching transactions

## [1.0.23]

- Add birthdate to customer information in Merchant API

## [1.0.22]

- Add browser information to Processor API
- Add 3DSv2 data to Processor API

## [1.0.21]

- Add agreement configuration to Processor API

## [1.0.20]

- Fix optional discount field handling

## [1.0.19]

- Fix multi-threading issues while parsing XML documents 

## [1.0.18]

- Restores `Description` in `/getTerminals` response to be able to customize the display of payment methods (originally introduced in 1.0.15)

## [1.0.17]

- Exposes `Translations` in `/getTerminals` response in order to support custom field translations per language

## [1.0.16]

- Exposes `SupportedPrimaryPaymentAuthTypes` in `/getTerminals` response in order to filter out payment methods not matched with auth type from session

## [1.0.15]

- Exposes `LogoUrl` and `Description` in `/getTerminals` response to be able to customize the display of payment methods

## [1.0.14]

- Add `productUrl` field to `OrderLine` to be included in /createPaymentRequest

## [1.0.13]

- Exposes `ShopName` in `/getTerminals` response to be able to filter payment methods by shop name

## [1.0.12]

- Add `retentionParameter` field to `AgreementConfig`

## [1.0.11]

- Exposes `SupportedAgreementTypes` in `/getTerminals` response in order filter out non-agreement payment methods if session relates to agreement payment type


## [1.0.10]

- Exposes `appUrl` in `/createPaymentRequest` response in order to support app redirect for mobile requests

## [1.0.9]

- Exposes `Products` in `/getTerminals` in order to support multi-product payment providers

## [1.0.8]

- Exposes `CanUseCredit` and `CanIssueNewCredit` in `/getTerminals` to comply with the newest Finnish Consumer protection act changes

## [1.0.7]

- Fixes for retrieving PrimaryMethod object in Terminal

## [1.0.6]

- Support for PrimaryMethod object in Terminal

## [1.0.5]

- Supports API changes for getTerminals

## [1.0.4]

- Supports API changes from 20230412
- Support enforced HTTP methods

## [1.0.3]

- Supports API changes from 20221026
- Support new `method` list in `/getTerminals`

## [1.0.2]

- Add support for Apple Pay
- Add support for new 'Agreements Engine' parameters

## [1.0.1]

- Update format of the User-Agent header

## [1.0.0]

- Supports API changes from 20210324
- Add support to agreements using agreement_type
