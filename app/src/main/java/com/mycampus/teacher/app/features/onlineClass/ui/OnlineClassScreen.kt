package com.mycampus.teacher.app.features.onlineClass.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.onlineClass.domain.model.OnlineClass
import com.mycampus.teacher.app.features.onlineClass.domain.model.Status
import com.mycampus.teacher.app.features.onlineClass.ui.component.LiveSessionTab
import com.mycampus.teacher.app.features.onlineClass.ui.component.MyClassesTab
import com.mycampus.teacher.app.features.onlineClass.ui.component.ScheduleTab
import java.time.LocalDate


@Composable
fun OnlineClassScreen() {
	val context = LocalContext.current
	var selectedDate by remember { mutableStateOf(LocalDate.now()) }
	var isVideoOn by remember { mutableStateOf(true) }
	var isAudioOn by remember { mutableStateOf(true) }
	var tabIndex by remember { mutableIntStateOf(0) }
	
	var classes by remember {
		mutableStateOf(
			listOf(
				OnlineClass(
					id = "1",
					title = "Algebra Fundamentals",
					subject = "Mathematics",
					className = "Grade 9A",
					date = LocalDate.now(),
					time = "10:00 AM",
					duration = 45,
					status = Status.Live,
					participants = 23,
					maxParticipants = 30,
					meetingLink = "https://meet.example.com/abc-123"
				), OnlineClass(
					id = "2",
					title = "Geometry Basics",
					subject = "Mathematics",
					className = "Grade 9B",
					date = LocalDate.now(),
					time = "2:00 PM",
					duration = 45,
					status = Status.Upcoming,
					participants = 0,
					maxParticipants = 30
				), OnlineClass(
					id = "3",
					title = "Statistics Overview",
					subject = "Mathematics",
					className = "Grade 10A",
					date = LocalDate.now(),
					time = "11:00 AM",
					duration = 60,
					status = Status.Ended,
					participants = 28,
					maxParticipants = 30
				)
			)
		)
	}
	
	val tabs = listOf("My Classes", "Live Session", "Schedule New")
	
	Column(modifier = Modifier.padding(16.dp)) {
		Text("Online Classes", style = MaterialTheme.typography.headlineMedium)
		Text(
			"Manage your virtual classroom sessions.",
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.onSurfaceVariant
		)
		
		Spacer(Modifier.height(16.dp))
		
		ScrollableTabRow(selectedTabIndex = tabIndex) {
			tabs.forEachIndexed { index, title ->
				Tab(selected = tabIndex == index, onClick = { tabIndex = index }, text = { Text(title) })
			}
		}
		
		Spacer(Modifier.height(16.dp))
		
		when (tabIndex) {
			0 -> MyClassesTab(classes, onStart = { id ->
				classes = classes.map { if (it.id == id) it.copy(status = Status.Live) else it }
				Toast.makeText(context, "Class started successfully!", Toast.LENGTH_SHORT).show()
			}, onEnd = { id ->
				classes = classes.map { if (it.id == id) it.copy(status = Status.Ended) else it }
				Toast.makeText(context, "Class ended", Toast.LENGTH_SHORT).show()
			})
			
			1 -> LiveSessionTab(
				liveClass = classes.find { it.status == Status.Live },
				isVideoOn = isVideoOn,
				isAudioOn = isAudioOn,
				onToggleVideo = { isVideoOn = !isVideoOn },
				onToggleAudio = { isAudioOn = !isAudioOn },
				onEnd = { id ->
					classes = classes.map { if (it.id == id) it.copy(status = Status.Ended) else it }
				})
			
			2 -> ScheduleTab(selectedDate, onDateSelected = { selectedDate = it }, onSchedule = {
				Toast.makeText(context, "Class scheduled successfully!", Toast.LENGTH_SHORT).show()
			})
		}
	}
}






