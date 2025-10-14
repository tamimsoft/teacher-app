package com.mycampus.teacher.app.features.auth.data.remote.model

import com.google.gson.annotations.SerializedName
import com.mycampus.teacher.app.features.auth.data.local.SchoolEntity

data class School(
	@SerializedName("school_id") val schoolId: String,
	@SerializedName("school_short_name") val schoolShortName: String,
	@SerializedName("school_name") val schoolName: String,
	@SerializedName("logo") val logo: String
)

fun School.toDomain(): SchoolEntity {
	return SchoolEntity(
		id = this.schoolId.toIntOrNull() ?: 0,
		name = this.schoolName,
		shortName = this.schoolShortName,
		logoUrl = this.logo
	)
}