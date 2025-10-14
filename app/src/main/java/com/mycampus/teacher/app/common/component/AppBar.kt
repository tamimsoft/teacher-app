package com.mycampus.teacher.app.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mycampus.teacher.R
import com.mycampus.teacher.app.core.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	modifier: Modifier = Modifier,
	title: String,
	subtitle: String? = null,
	isHome: Boolean = false,
	avatarUrl: String? = null,
	navigationIcon: @Composable (() -> Unit)? = null,
	actions: @Composable (RowScope.() -> Unit)? = null,
) {
	TopAppBar(
		title = {
		Row(verticalAlignment = Alignment.CenterVertically) {
			// if Avatar Image is not exist the show placeholder
			val painter = if (!avatarUrl.isNullOrEmpty()) {
				rememberAsyncImagePainter(avatarUrl)
			} else {
				painterResource(id = R.drawable.placeholder)
			}
			// Avatar Image
			if (isHome) {
				Image(
					painter = painter,
					contentDescription = "Avatar",
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.size(40.dp)
						.clip(CircleShape)
						.border(
							width = 2.dp, color = Color.White,
						)
				)
				Spacer(modifier = Modifier.width(12.dp))
			}
			
			
			// Title & Subtitle
			Column {
				Text(
					text = title,
					style = MaterialTheme.typography.titleLarge,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
				subtitle?.let {
					Text(
						text = it,
						style = MaterialTheme.typography.bodySmall,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis
					)
				}
			}
		}
	},
		navigationIcon = navigationIcon ?: {},
		actions = { actions?.invoke(this) },
		modifier = modifier
	)
}

@PreviewLightDark
@Composable
fun HomeAppBarPreview() {
	AppTheme {
		AppBar(
			title = "Tamim Hasan", subtitle = "Flutter Developer", actions = {
				IconButton(onClick = { /* Do something */ }) {
					Icon(Icons.Default.Search, contentDescription = "Search")
				}
			})
	}
}


@PreviewLightDark
@Composable
fun AppBarPreview() {
	AppTheme {
		AppBar(
			title = "Attendance",
			subtitle = "Mark daily attendance for your classes.",
			navigationIcon = {
				IconButton(onClick = {
					//navController.popBackStack()
				}) {
					Icon(
						imageVector = Icons.AutoMirrored.Filled.ArrowBack,
						contentDescription = "Localized description"
					)
				}
			},
		)
	}
}