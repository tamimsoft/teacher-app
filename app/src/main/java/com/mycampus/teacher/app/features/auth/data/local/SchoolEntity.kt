package com.mycampus.teacher.app.features.auth.data.local

data class SchoolEntity(
	val id: Int, val shortName: String, val name: String, val logoUrl: String
) {
	companion object {
		val EMPTY = SchoolEntity(0, "", "", "")
	}
	val isValid: Boolean
		get() = id != 0 && name.isNotBlank()
}