package com.example.modoexamen.features.feed.data.model

import com.google.gson.annotations.SerializedName

class FeedResponse : ArrayList<FeedResponseItem>()

data class FeedResponseItem(
    val amount: Double,
    val category: String,
    @SerializedName("channel_id") val channelId: String,
    @SerializedName("entity_id") val entityId: String,
    @SerializedName("feed_data") val feedData: FeedData,
    val id: String,
    val image: String,
    @SerializedName("partition_group") val partitionGroup: Int,
    val status: String,
    val subtype: String,
    val timestamp: String,
    val type: String,
    @SerializedName("user_id") val userId: String
)

data class FeedData(
    val _datadog: Datadog,
    val acceptor: Acceptor,
    @SerializedName("acceptor_name") val acceptorName: String,
    val account: Account,
    val acquirer: Acquirer,
    @SerializedName("acquirer_name") val acquirerName: String,
    val card: Card,
    val category: String,
    @SerializedName("channel_id") val channelId: String,
    @SerializedName("event_date") val eventDate: String,
    @SerializedName("event_type") val eventType: String,
    val payment: Payment,
    val qr: Qr,
    val user: User,
    @SerializedName("user_id") val userId: String
)

data class Datadog(
    @SerializedName("x-datadog-parent-id") val xDatadogParentId: String,
    @SerializedName("x-datadog-sampling-priority") val xDatadogSamplingPriority: String,
    @SerializedName("x-datadog-tags") val xDatadogTags: String,
    @SerializedName("x-datadog-trace-id") val xDatadogTraceId: String
)

data class Acceptor(
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("cancellation_type") val cancellationType: String,
    @SerializedName("encrypted_path") val encryptedPath: String,
    @SerializedName("encryption_enabled") val encryptionEnabled: Boolean,
    val id: String,
    val name: String
)

data class Account(
    val bank: Bank,
    val id: String,
    @SerializedName("last_digits") val lastDigits: String,
    val type: String
)

data class Acquirer(
    @SerializedName("base_url") val baseUrl: String,
    @SerializedName("cancellation_type") val cancellationType: String,
    @SerializedName("encrypted_path") val encryptedPath: String,
    @SerializedName("encryption_enabled") val encryptionEnabled: Boolean,
    val id: String,
    val name: String,
    @SerializedName("refund_limit_days") val refundLimitDays: Int
)

data class Card(
    val bank: BankX,
    @SerializedName("bank_id") val bankId: String,
    val bin: String,
    @SerializedName("created_at") val createdAt: String,
    val expiry: String,
    val favourite: Boolean,
    val id: String,
    @SerializedName("issuer_name") val issuerName: String,
    @SerializedName("last_digits") val lastDigits: String,
    val source: String,
    val type: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("user_id") val userId: String
)

data class Payment(
    @SerializedName("acceptor_id") val acceptorId: String,
    @SerializedName("account_id") val accountId: String,
    @SerializedName("acquirer_id") val acquirerId: String,
    @SerializedName("additional_info") val additionalInfo: AdditionalInfo,
    val amount: Double,
    @SerializedName("card_id") val cardId: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("currency_code") val currencyCode: String,
    @SerializedName("establishment_id") val establishmentId: String,
    @SerializedName("external_payment_id") val externalPaymentId: String,
    val id: String,
    val installments: String,
    @SerializedName("installments_amount") val installmentsAmount: Int,
    val mcc: String,
    @SerializedName("merchant_cuit") val merchantCuit: String,
    @SerializedName("merchant_id") val merchantId: String,
    @SerializedName("merchant_name") val merchantName: String,
    @SerializedName("operation_code") val operationCode: String,
    @SerializedName("operation_date") val operationDate: String,
    @SerializedName("operation_error") val operationError: String,
    @SerializedName("original_error_code") val originalErrorCode: String,
    @SerializedName("payment_method_type") val paymentMethodType: String,
    @SerializedName("plan_text") val planText: String,
    @SerializedName("point_of_initiation") val pointOfInitiation: String,
    @SerializedName("reference_code") val referenceCode: String,
    val status: String,
    @SerializedName("terminal_id") val terminalId: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class Qr(
    @SerializedName("acceptorCards") val acceptorCards: String,
    val acquirer: String,
    @SerializedName("administratorCuit") val administratorCuit: String,
    @SerializedName("countryCode") val countryCode: String,
    val crc: String,
    val currency: String,
    @SerializedName("customFields") val customFields: CustomFieldsX,
    @SerializedName("customerLabel") val customerLabel: String,
    @SerializedName("dynamic") val dynamic: Boolean,
    @SerializedName("installments") val installments: Installments,
    @SerializedName("installmentsFinancingEnable") val installmentsFinancingEnable: Boolean,
    val mcc: String,
    @SerializedName("merchantCity") val merchantCity: String,
    @SerializedName("merchantName") val merchantName: String,
    @SerializedName("merchantTaxId") val merchantTaxId: String,
    @SerializedName("onlyBankCards") val onlyBankCards: Boolean,
    @SerializedName("onlyDebit") val onlyDebit: Boolean,
    @SerializedName("operationDate") val operationDate: String,
    @SerializedName("operationType") val operationType: String,
    @SerializedName("rawQR") val rawQR: String,
    @SerializedName("referenceLabel") val referenceLabel: Int,
    @SerializedName("reverseDomain") val reverseDomain: String,
    @SerializedName("selectPaymentMethod") val selectPaymentMethod: Boolean,
    val status: String,
    @SerializedName("storeLabel") val storeLabel: String,
    @SerializedName("supportedPaymentMethods") val supportedPaymentMethods: List<SupportedPaymentMethod>,
    @SerializedName("supportedPaymentTypes") val supportedPaymentTypes: List<String>,
    @SerializedName("terminalId") val terminalId: String,
    @SerializedName("totalAmount") val totalAmount: Int,
    @SerializedName("transactionAmount") val transactionAmount: Int,
    @SerializedName("unsupportedPaymentMethods") val unsupportedPaymentMethods: List<UnsupportedPaymentMethod>
)

data class User(
    val attributes: List<Attribute>,
    @SerializedName("created_at") val createdAt: String,
    val dni: String,
    @SerializedName("failed_login_attempts") val failedLoginAttempts: Int,
    @SerializedName("first_name") val firstName: String,
    val gender: String,
    val id: String,
    @SerializedName("identity_confidence") val identityConfidence: Int,
    @SerializedName("identity_validation") val identityValidation: Boolean,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("receive_benefits") val receiveBenefits: Boolean,
    val status: String,
    val type: String
)

data class Bank(
    @SerializedName("image_url") val imageUrl: String,
    val name: String
)

data class BankX(
    @SerializedName("bcra_description") val bcraDescription: String,
    @SerializedName("bcra_id") val bcraId: Int,
    @SerializedName("card_color") val cardColor: String,
    @SerializedName("feature_flags") val featureFlags: FeatureFlags,
    val id: String,
    @SerializedName("image_url") val imageUrl: String,
    val name: String,
    val partnered: Boolean,
    val status: String
)

data class FeatureFlags(
    @SerializedName("automaticCardLinking") val automaticCardLinking: Boolean,
    @SerializedName("cardDetails") val cardDetails: Boolean,
    @SerializedName("flowFeatureFlags") val flowFeatureFlags: FlowFeatureFlags,
    @SerializedName("getCvv") val getCvv: Boolean,
    @SerializedName("loginNotification") val loginNotification: Boolean,
    @SerializedName("mtls") val mtls: Boolean,
    @SerializedName("simplifiedOnboard") val simplifiedOnboard: Boolean,
    @SerializedName("throughTransferMs") val throughTransferMs: Boolean,
    @SerializedName("transferWithFingerprint") val transferWithFingerprint: Boolean,
    @SerializedName("validateCard") val validateCard: Boolean
)

data class FlowFeatureFlags(
    @SerializedName("destinationAccountType") val destinationAccountType: DestinationAccountType
)

data class DestinationAccountType(
    @SerializedName("virtual") val virtual: Boolean
)

data class AdditionalInfo(
    @SerializedName("annualEffectiveRate") val annualEffectiveRate: Int,
    @SerializedName("annualNominalRate") val annualNominalRate: Int,
    @SerializedName("cancellationResponse") val cancellationResponse: CancellationResponse,
    @SerializedName("cardAuthorizationCode") val cardAuthorizationCode: String,
    @SerializedName("data") val data: Data,
    val errors: List<Error>,
    @SerializedName("establishment_id") val establishmentId: String,
    @SerializedName("gatewaySiteTransactionId") val gatewaySiteTransactionId: String,
    @SerializedName("gatewayTransactionId") val gatewayTransactionId: String,
    @SerializedName("hardwareId") val hardwareId: String,
    @SerializedName("intention_id") val intentionId: Int,
    @SerializedName("ipAddress") val ipAddress: String,
    @SerializedName("meta") val meta: Meta,
    @SerializedName("paymentInstallments") val paymentInstallments: PaymentInstallments,
    @SerializedName("payment_id") val paymentId: Long,
    @SerializedName("qr_id") val qrId: String,
    @SerializedName("rawParsedQR") val rawParsedQR: RawParsedQR,
    val rewards: List<Reward>,
    @SerializedName("status_details") val statusDetails: StatusDetailsX,
    @SerializedName("terminal_data") val terminalData: TerminalData,
    val ticket: String,
    @SerializedName("totalFinancialCost") val totalFinancialCost: Int,
    @SerializedName("transaction_datetime") val transactionDatetime: String
)

data class CancellationResponse(
    @SerializedName("establishment_id") val establishmentId: String,
    @SerializedName("intention_id") val intentionId: Int,
    @SerializedName("payment_id") val paymentId: Long,
    @SerializedName("qr_id") val qrId: String,
    @SerializedName("status_details") val statusDetails: StatusDetailsX,
    @SerializedName("terminal_data") val terminalData: TerminalData,
    @SerializedName("transaction_datetime") val transactionDatetime: String
)

data class Data(
    val attributes: Attributes,
    val id: String,
    val type: String
)

data class Error(
    val code: String,
    val detail: String,
    val id: String,
    val link: String,
    val status: Int,
    val title: String
)

data class Meta(
    @SerializedName("referencePaymentId") val referencePaymentId: String,
    val time: Int
)

data class PaymentInstallments(
    val amount: Double,
    @SerializedName("financingData") val financingData: FinancingData,
    val label: String,
    @SerializedName("planText") val planText: String,
    val quantity: Int,
    @SerializedName("realQuantity") val realQuantity: Int
)

data class RawParsedQR(
    @SerializedName("acceptorCards") val acceptorCards: String,
    val acquirer: String,
    @SerializedName("administratorCuit") val administratorCuit: String,
    @SerializedName("countryCode") val countryCode: String,
    val crc: String,
    val currency: String,
    @SerializedName("customFields") val customFields: CustomFields,
    @SerializedName("customerLabel") val customerLabel: String,
    @SerializedName("dynamic") val dynamic: Boolean,
    @SerializedName("installments") val installments: Installments,
    @SerializedName("installmentsFinancingEnable") val installmentsFinancingEnable: Boolean,
    val mcc: String,
    @SerializedName("merchantCity") val merchantCity: String,
    @SerializedName("merchantName") val merchantName: String,
    @SerializedName("merchantTaxId") val merchantTaxId: String,
    @SerializedName("onlyBankCards") val onlyBankCards: Boolean,
    @SerializedName("onlyDebit") val onlyDebit: Boolean,
    @SerializedName("operationDate") val operationDate: String,
    @SerializedName("operationType") val operationType: String,
    @SerializedName("rawQR") val rawQR: String,
    @SerializedName("referenceLabel") val referenceLabel: Int,
    @SerializedName("reverseDomain") val reverseDomain: String,
    @SerializedName("selectPaymentMethod") val selectPaymentMethod: Boolean,
    val status: String,
    @SerializedName("storeLabel") val storeLabel: String,
    @SerializedName("supportedPaymentMethods") val supportedPaymentMethods: List<SupportedPaymentMethod>,
    @SerializedName("supportedPaymentTypes") val supportedPaymentTypes: List<String>,
    @SerializedName("terminalId") val terminalId: String,
    @SerializedName("totalAmount") val totalAmount: Int,
    @SerializedName("transactionAmount") val transactionAmount: Int,
    @SerializedName("unsupportedPaymentMethods") val unsupportedPaymentMethods: List<UnsupportedPaymentMethod>
)

data class Reward(
    val amount: Double,
    @SerializedName("calculatedCap") val calculatedCap: Int,
    @SerializedName("cardId") val cardId: String,
    @SerializedName("discountMode") val discountMode: String,
    @SerializedName("forceLabel") val forceLabel: Any,
    val installments: List<Any>,
    @SerializedName("paymentMethodId") val paymentMethodId: String,
    @SerializedName("promoValue") val promoValue: Int,
    @SerializedName("promoValueType") val promoValueType: String,
    @SerializedName("promotionId") val promotionId: String,
    val slug: String,
    val title: String,
    @SerializedName("totalRewardAmount") val totalRewardAmount: Double,
    @SerializedName("trigger_type") val triggerType: String
)

data class StatusDetailsX(
    @SerializedName("card_authorization_code") val cardAuthorizationCode: String,
    @SerializedName("card_reference_number") val cardReferenceNumber: String,
    val description: String,
    val status: String,
    @SerializedName("status_code") val statusCode: String,
    @SerializedName("ticket_footer") val ticketFooter: String
)

data class TerminalData(
    @SerializedName("terminal_number") val terminalNumber: String,
    @SerializedName("ticket_number") val ticketNumber: Int,
    @SerializedName("trace_number") val traceNumber: Int
)

data class Attributes(
    @SerializedName("date") val date: String,
    @SerializedName("paymentMethod") val paymentMethod: PaymentMethod,
    @SerializedName("qr") val qr: String,
    @SerializedName("referencePaymentId") val referencePaymentId: String,
    @SerializedName("sellerName") val sellerName: String,
    val status: List<Any>,
    @SerializedName("totalAmount") val totalAmount: TotalAmount,
    @SerializedName("walletId") val walletId: String
)

data class PaymentMethod(
    @SerializedName("debinQrInfo") val debinQrInfo: List<Any>
)

data class TotalAmount(
    val currency: String,
    val value: Int
)

data class FinancingData(
    @SerializedName("annualEffectiveRate") val annualEffectiveRate: Double,
    @SerializedName("annualNominalRate") val annualNominalRate: Double,
    @SerializedName("totalFinancialCost") val totalFinancialCost: Double
)

data class CustomFields(
    val checkout: String,
    @SerializedName("establishmentTransactionDetails") val establishmentTransactionDetails: List<EstablishmentTransactionDetail>,
    @SerializedName("paymentInformation") val paymentInformation: List<PaymentInformation>
)

data class Installments(
    val amount: Double,
    val label: String,
    @SerializedName("planText") val planText: String,
    val quantity: Int,
    @SerializedName("realQuantity") val realQuantity: Int
)

data class SupportedPaymentMethod(
    @SerializedName("cashout_available") val cashoutAvailable: Boolean,
    val id: String,
    @SerializedName("network_token_id") val networkTokenId: String,
    val type: String
)

data class UnsupportedPaymentMethod(
    val id: String,
    val reason: String,
    val type: String
)

data class EstablishmentTransactionDetail(
    @SerializedName("establishmentId") val establishmentId: String,
    val scheme: String,
    @SerializedName("ticketNumber") val ticketNumber: String,
    @SerializedName("traceNumber") val traceNumber: String
)

data class PaymentInformation(
    @SerializedName("establishmentId") val establishmentId: String,
    val installments: List<Installment>,
    val scheme: String,
    val type: String
)

data class Installment(
    @SerializedName("annualNominalRate") val annualNominalRate: Double,
    val quantity: Int,
    @SerializedName("totalAmount") val totalAmount: Double,
    @SerializedName("totalFinancialCost") val totalFinancialCost: Double
)

data class CustomFieldsX(
    val checkout: String,
    @SerializedName("establishmentTransactionDetails") val establishmentTransactionDetails: List<EstablishmentTransactionDetail>,
    @SerializedName("paymentInformation") val paymentInformation: List<PaymentInformationX>
)

data class PaymentInformationX(
    @SerializedName("establishmentId") val establishmentId: String,
    val installments: List<Installment>,
    val scheme: String,
    val type: String
)

data class Attribute(
    @SerializedName("attribute_type") val attributeType: String,
    @SerializedName("attribute_value") val attributeValue: String,
    @SerializedName("created_at") val createdAt: String,
    val id: String,
    val source: Any,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("user_id") val userId: String,
    val validated: Any
)
