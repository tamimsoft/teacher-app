package com.mycampus.teacher.app.features.onlineClass.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.onlineClass.domain.model.OnlineClass

@Composable
fun LiveSessionTab(
	liveClass: OnlineClass?,
	isVideoOn: Boolean,
	isAudioOn: Boolean,
	onToggleVideo: () -> Unit,
	onToggleAudio: () -> Unit,
	onEnd: (String) -> Unit
) {
	if (liveClass == null) {
		Text("No live session right now.")
		return
	}
	
	Card(modifier = Modifier.fillMaxWidth()) {
		Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
			Text(liveClass.title, fontWeight = FontWeight.Bold)
			Spacer(Modifier.height(16.dp))
			Box(
				Modifier
					.fillMaxWidth()
					.height(200.dp)
					.background(Color.Gray),
				contentAlignment = Alignment.Center
			) {
				Text("Live Video Preview")
			}
			
			Spacer(Modifier.height(16.dp))
			
			Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
				IconToggleButton(checked = isVideoOn, onCheckedChange = { onToggleVideo() }) {
					Icon(
						Icons.Default.Videocam,
						contentDescription = null,
						tint = if (isVideoOn) Color.Green else Color.Red
					)
				}
				IconToggleButton(checked = isAudioOn, onCheckedChange = { onToggleAudio() }) {
					Icon(
						Icons.Default.Mic,
						contentDescription = null,
						tint = if (isAudioOn) Color.Green else Color.Red
					)
				}
			}
			
			Spacer(Modifier.height(16.dp))
			Button(onClick = { onEnd(liveClass.id) }) { Text("End Class") }
		}
	}
}