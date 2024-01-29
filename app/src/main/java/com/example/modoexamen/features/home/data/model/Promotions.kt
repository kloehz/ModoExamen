package com.example.modoexamen.features.home.data.model

import com.google.gson.annotations.SerializedName

data class Promotions(
    @SerializedName("cards") val cards: List<PromotionCard>,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("current_page_cards_count") val currentPageCardsCount: Int,
    @SerializedName("slot_id") val slotId: String,
    @SerializedName("slot_title") val slotTitle: String,
    @SerializedName("total_cards_count") val totalCardsCount: String,
    @SerializedName("total_pages") val totalPages: Int
)

data class PromotionCard(
    @SerializedName("backgroundcolor") val backgroundColor: Any,
    @SerializedName("calculated_status") val calculatedStatus: String,
    @SerializedName("categories_whitelist") val categoriesWhitelist: CategoriesWhitelist,
    @SerializedName("content") val content: Content,
    @SerializedName("cta_type") val ctaType: String,
    @SerializedName("cta_value") val ctaValue: String,
    @SerializedName("daily_start_time") val dailyStartTime: String,
    @SerializedName("daily_stop_time") val dailyStopTime: String,
    @SerializedName("days_of_week") val daysOfWeek: String,
    @SerializedName("exclusiveness") val exclusiveness: String,
    @SerializedName("extra_params") val extraParams: Any,
    @SerializedName("id") val id: String,
    @SerializedName("landscape_app") val landscapeApp: String,
    @SerializedName("landscape_bgcolor") val landscapeBgColor: String,
    @SerializedName("landscape_web") val landscapeWeb: String,
    @SerializedName("payment_flow") val paymentFlow: String,
    @SerializedName("promo_id") val promoId: String,
    @SerializedName("promo_visibility") val promoVisibility: String,
    @SerializedName("search_tags") val searchTags: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("stop_date") val stopDate: String,
    @SerializedName("suggested_order") val suggestedOrder: Int,
    @SerializedName("title") val title: String,
    @SerializedName("trigger_type") val triggerType: String,
    @SerializedName("type") val type: String,
    @SerializedName("where") val where: String
)

data class CategoriesWhitelist(
    @SerializedName("categories") val categories: List<Category>
)

data class Content(
    @SerializedName("extra_row") val extraRow: Any,
    @SerializedName("image") val image: Image,
    @SerializedName("row") val row: List<Row>,
    @SerializedName("tag_extras") val tagExtras: List<TagExtra>
)

data class Category(
    @SerializedName("map_category") val mapCategory: Int,
    @SerializedName("sub_categories") val subCategories: List<Int>
)

data class Image(
    @SerializedName("optional_images_pack") val optionalImagesPack: OptionalImagesPack,
    @SerializedName("primary_backgroundcolor") val primaryBackgroundColor: String,
    @SerializedName("primary_image") val primaryImage: String,
    @SerializedName("primary_position") val primaryPosition: List<PrimaryPosition>,
    @SerializedName("secondary_backgroundcolor") val secondaryBackgroundColor: String,
    @SerializedName("secondary_image") val secondaryImage: String
)

data class Row(
    @SerializedName("backgroundcolor") val backgroundColor: String,
    @SerializedName("forecolor") val foreColor: String,
    @SerializedName("text") val text: String
)

data class TagExtra(
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String,
    @SerializedName("value") val value: Boolean
)

data class OptionalImagesPack(
    @SerializedName("landscape_app") val landscapeApp: String,
    @SerializedName("landscape_bgcolor") val landscapeBgColor: String,
    @SerializedName("landscape_web") val landscapeWeb: String
)

data class PrimaryPosition(
    @SerializedName("x") val x: Int,
    @SerializedName("y") val y: Int
)
