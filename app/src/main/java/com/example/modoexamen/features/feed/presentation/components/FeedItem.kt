package com.example.modoexamen.features.feed.presentation.components

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.modoexamen.R

class FeedItem : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FeedItemComposable() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        GlideImage(
            model = "https://hips.hearstapps.com/hmg-prod/images/jujutsu-kaisen-choso-itadori-2-6533c70dd00ac.jpg",
            contentDescription = "Failed",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(
                text = "YPF",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.primaryBlack)
            )
            Text(
                text = "Pagaste * 10:30",
                fontSize = 14.sp,
                color = colorResource(R.color.secondaryGray60)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "-$4.400,50",
            fontSize = 20.sp,
            color = colorResource(R.color.primaryBlack)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedItemComposablePreview() {
    FeedItemComposable()
}