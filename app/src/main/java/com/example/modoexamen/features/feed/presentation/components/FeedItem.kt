package com.example.modoexamen.features.feed.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.modoexamen.R
import com.example.modoexamen.features.feed.data.model.FeedResponseItem
import com.example.modoexamen.shared.components.shimmerBackground
import java.math.RoundingMode
import java.text.DecimalFormat

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FeedItemComposable(feedItem: FeedResponseItem?, showShimmer: Boolean = false) {
    val decimalFormat = DecimalFormat("#0.00")
    decimalFormat.roundingMode = RoundingMode.CEILING
    var amountFormatted: String? = null
    feedItem?.feedData?.payment?.amount?.let {
        amountFormatted = decimalFormat.format(feedItem.feedData.payment.amount)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        GlideImage(
            model = feedItem?.image
                ?: if (showShimmer) "" else "https://assets.mobile.preprod.playdigital.com.ar/images/merchants/categories/Ctg-OtrosyTodos.png",
            contentDescription = "Failed",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .defaultMinSize(minWidth = 48.dp)
                .background(
                    shimmerBackground(targetValue = 1300f, showShimmer = showShimmer),
                    CircleShape
                )
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = feedItem?.feedData?.payment?.merchantName ?: "",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.primaryBlack),
                modifier = Modifier
                    .defaultMinSize(minWidth = 70.dp)
                    .background(shimmerBackground(targetValue = 1300f, showShimmer = showShimmer))
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
            ) {}
            Text(
                text = feedItem?.type ?: "",
                fontSize = 14.sp,
                color = colorResource(R.color.secondaryGray60),
                modifier = Modifier
                    .defaultMinSize(minWidth = 100.dp)
                    .background(shimmerBackground(targetValue = 1300f, showShimmer = showShimmer))
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = amountFormatted ?: "",
            fontSize = 20.sp,
            color = colorResource(R.color.primaryBlack),
            textAlign = TextAlign.End,
            modifier = Modifier
                .defaultMinSize(minWidth = 100.dp)
                .background(shimmerBackground(targetValue = 1300f, showShimmer = showShimmer))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedItemComposablePreview() {}
