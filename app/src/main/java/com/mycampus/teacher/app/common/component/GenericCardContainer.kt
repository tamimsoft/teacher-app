//package com.mycampus.teacher.app.common.component
//
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScope
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.PreviewLightDark
//import androidx.compose.ui.unit.dp
//import com.mycampus.teacher.app.core.theme.AppTheme
//
//// Generic Card container
//@Composable
//fun Card(
//	modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit
//) {
//	Column(
//		modifier = modifier
//			.fillMaxWidth()
//			.shadow(1.dp, RoundedCornerShape(12.dp))
//			.background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
//	) {
//		content()
//	}
//}
//
//// Card Header
//@Composable
//fun CardHeader(
//	modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit
//) {
//	Column(
//		modifier = modifier
//			.fillMaxWidth()
//			.padding(16.dp),
//		verticalArrangement = Arrangement.spacedBy(4.dp)
//	) {
//		content()
//	}
//}
//
//// Card Title
//@Composable
//fun CardTitle(
//	text: String, modifier: Modifier = Modifier
//) {
//	Text(
//		text = text,
//		modifier = modifier,
//		style = MaterialTheme.typography.titleLarge,
//		maxLines = 1,
//		overflow = TextOverflow.Ellipsis
//	)
//}
//
//// Card Description
//@Composable
//fun CardDescription(
//	text: String, modifier: Modifier = Modifier
//) {
//	Text(
//		text = text,
//		modifier = modifier,
//		style = MaterialTheme.typography.bodySmall,
//		color = MaterialTheme.colorScheme.onSurfaceVariant
//	)
//}
//
//// Card Content
//@Composable
//fun CardContent(
//	modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit
//) {
//	Column(
//		modifier = modifier
//			.fillMaxWidth()
//			.padding(horizontal = 16.dp, vertical = 8.dp)
//	) {
//		content()
//	}
//}
//
//// Card Footer
//@Composable
//fun CardFooter(
//	modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit
//) {
//	Row(
//		modifier = modifier
//			.fillMaxWidth()
//			.padding(horizontal = 16.dp, vertical = 8.dp),
//		verticalAlignment = Alignment.CenterVertically,
//		horizontalArrangement = Arrangement.Start
//	) {
//		content()
//	}
//}
//
//@PreviewLightDark
//@Composable
//fun ExampleCardUsage() {
//	AppTheme {
//		Card(modifier = Modifier.padding(16.dp)) {
//
//			// Card Header
//			CardHeader {
//				CardTitle(text = "Card Title")
//				CardDescription(text = "This is a description for the card.")
//			}
//
//			// Card Content
//			CardContent {
//				Text("Here you can put any content inside the card.")
//				Spacer(modifier = Modifier.height(8.dp))
//				Text("You can add multiple items like in a Column.")
//			}
//
//			// Card Footer
//			CardFooter {
//				Text("Footer Item 1", color = Color.Blue)
//				Spacer(modifier = Modifier.width(8.dp))
//				Text("Footer Item 2", color = Color.Red)
//			}
//		}
//	}
//}