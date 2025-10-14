package com.mycampus.teacher.app.features.onlineClass.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.onlineClass.domain.model.OnlineClass
import com.mycampus.teacher.app.features.onlineClass.domain.model.Status

@Composable
fun MyClassesTab(
	classes: List<OnlineClass>, onStart: (String) -> Unit, onEnd: (String) -> Unit
) {
	LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		items(classes.size) { index ->
			val onlineClass = classes[index]
			Card(modifier = Modifier.fillMaxWidth()) {
				Column(Modifier.padding(16.dp)) {
					Row(
						horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
					) {
						Column {
							Text(onlineClass.title, fontWeight = FontWeight.Bold)
							Text(
								"${onlineClass.className} • ${onlineClass.subject}",
								style = MaterialTheme.typography.bodySmall
							)
						}
						StatusBadge(onlineClass.status)
					}
					Spacer(Modifier.height(8.dp))
					Text("Time: ${onlineClass.date} ${onlineClass.time}")
					Text("Duration: ${onlineClass.duration} minutes")
					Text("Participants: ${onlineClass.participants}/${onlineClass.maxParticipants}")
					
					Spacer(Modifier.height(8.dp))
					
					Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
						if (onlineClass.status == Status.Upcoming) {
							Button(onClick = { onStart(onlineClass.id) }) { Text("Start Class") }
						}
						if (onlineClass.status == Status.Live) {
							Button(onClick = { onEnd(onlineClass.id) }) { Text("End Class") }
						}
					}
				}
			}
		}
	}
}