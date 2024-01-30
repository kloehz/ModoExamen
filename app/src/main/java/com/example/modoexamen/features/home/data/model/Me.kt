package com.example.modoexamen.features.home.data.model

import com.google.gson.annotations.SerializedName

enum class AccountTypes(val code: String) {
    SAVINGS("CA"),
    CURRENT("CC");

    companion object {
        fun fromCode(code: String): AccountTypes? {
            return values().find { it.code == code }
        }
    }
}

data class Me(
    var accounts: MutableList<Account>,
    val cards: List<Card>,
    @SerializedName("created_at") val createdAt: String,
    val dni: String,
    val email: String,
    @SerializedName("email_validated") val emailValidated: Boolean,
    @SerializedName("first_name") val firstName: String,
    val gender: String,
    val id: String,
    @SerializedName("identity_validation") val identityValidation: Boolean,
    val image: String?,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("license_plates") val licensePlates: List<Any>,
    @SerializedName("member_get_members_amount") val memberGetMembersAmount: String,
    @SerializedName("member_get_members_max_amount") val memberGetMembersMaxAmount: String,
    @SerializedName("member_get_members_url") val memberGetMembersUrl: String,
    val name: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("receive_benefits") val receiveBenefits: Boolean,
    @SerializedName("suggested_banks") val suggestedBanks: List<SuggestedBank>,
    @SerializedName("suggested_banks_by_cards") val suggestedBanksByCards: List<Any>
)

data class Account(
    val bank: Bank,
    val createdAt: String,
    @SerializedName("currency_code") val currencyCode: String,
    val favourite: Boolean,
    val id: String,
    var balance: Float?,
    var isLoadingBalance: Boolean = true,
    @SerializedName("last_digits") val lastDigits: String,
    val schema: String,
    val type: AccountTypes
)

data class Card(
    val bank: Any,
    @SerializedName("bank_logo") val bankLogo: String,
    val bin: String,
    @SerializedName("card_art") val cardArt: CardArt,
    @SerializedName("card_color") val cardColor: String,
    val color: Any,
    @SerializedName("cvv_type") val cvvType: String,
    @SerializedName("details_available") val detailsAvailable: Boolean,
    @SerializedName("enrollment_type") val enrollmentType: String,
    val expired: Boolean,
    val expiry: String,
    val favourite: Boolean,
    val id: String,
    @SerializedName("issuer_background_logo") val issuerBackgroundLogo: String,
    @SerializedName("issuer_logo") val issuerLogo: String,
    @SerializedName("issuer_name") val issuerName: String,
    @SerializedName("last_digits") val lastDigits: String,
    val prepaid: Boolean,
    @SerializedName("recently_pushed") val recentlyGushed: Boolean,
    val type: String
)

data class SuggestedBank(
    @SerializedName("automatic_card_linking") val automaticCardLinking: Boolean,
    @SerializedName("can_link") val canLink: Boolean,
    val favourite: Boolean,
    val id: String,
    @SerializedName("image_url") val imageUrl: String,
    val name: String
)

data class Bank(
    val id: String,
    @SerializedName("image_url") val imageUrl: String,
    val name: String
)

data class CardArt(
    val active: Boolean
)