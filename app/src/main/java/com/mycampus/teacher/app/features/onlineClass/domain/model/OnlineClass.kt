package com.mycampus.teacher.app.features.onlineClass.domain.model

import java.time.LocalDate

enum class Status { Live, Upcoming, Ended }

data class OnlineClass(
	val id: String,
	val title: String,
	val subject: String,
	val className: String,
	val date: LocalDate,
	val time: String,
	val duration: Int,
	val status: Status,
	val participants: Int,
	val maxParticipants: Int,
	val meetingLink: String? = null
)