package com.mycampus.teacher.app.features.exam.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycampus.teacher.app.features.exam.domain.model.StudentMark

@Composable
fun AnalyticsTab(studentMarks: List<StudentMark>) {
	// Convert string marks to Float safely, ignore invalid ones
	val marks = studentMarks.mapNotNull { it.marks?.toFloatOrNull() }
	val average = if (marks.isNotEmpty()) marks.average().toInt() else 0
	val highest = marks.maxOrNull() ?: 0
	val lowest = marks.minOrNull() ?: 0
	val passRate = if (marks.isNotEmpty()) {
		(marks.count { it >= 50 } * 100 / marks.size)
	} else 0
	
	Column(
		modifier = Modifier.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(12.dp)
	) {
		Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			ExamStatCard(
				modifier = Modifier.weight(1f),
				icon = Icons.AutoMirrored.Filled.TrendingUp,
				value = average.toString(),
				label = "Average",
				iconTint = MaterialTheme.colorScheme.primary
			)
			ExamStatCard(
				modifier = Modifier.weight(1f),
				icon = Icons.Default.EmojiEvents,
				value = highest.toString(),
				label = "Highest",
				iconTint = Color(0xFF4CAF50)
			)
		}
		Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			ExamStatCard(
				modifier = Modifier
					.weight(1f),
				icon = Icons.Default.Warning,
				value = lowest.toString(),
				label = "Lowest",
				iconTint = Color(0xFFF59E0B)
			)
			ExamStatCard(
				modifier = Modifier.weight(1f),
				icon = Icons.Default.Percent,
				value = "$passRate%",
				label = "Pass Rate",
				iconTint = when {
					passRate >= 80 -> Color(0xFF4CAF50)
					passRate >= 60 -> Color(0xFFF59E0B)
					else -> Color(0xFFEF4444)
				}
			)
		}
		
		Card {
			Column(
				Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Text("Grade Distribution", fontWeight = FontWeight.Medium)
				listOf("A+", "A", "B+", "B", "C", "F").forEach { grade ->
					val count = studentMarks.count { it.grade == grade }
					val percentage = if (studentMarks.isNotEmpty()) (count * 100f / studentMarks.size) else 0f
					GradeDistributionBar(
						grade = grade, count = count, percentage = percentage
					)
				}
			}
		}
	}
}

@Composable
fun GradeDistributionBar(
	grade: String, count: Int, percentage: Float
) {
	Row(verticalAlignment = Alignment.CenterVertically) {
		Text(grade, Modifier.width(24.dp))
		LinearProgressIndicator(
			progress = { percentage / 100f },
			modifier = Modifier
				.weight(1f)
				.height(6.dp),
			color = ProgressIndicatorDefaults.linearColor,
			trackColor = ProgressIndicatorDefaults.linearTrackColor,
			strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
		)
		Spacer(Modifier.width(8.dp))
		Text(count.toString(), fontSize = 12.sp)
	}
}