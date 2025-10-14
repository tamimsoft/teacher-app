package com.mycampus.teacher.app.config.route

sealed class Route(val path: String) {
    object Login : Route("login")
    object Dashboard : Route("dashboard")
    object Attendance : Route("attendance")
    object MarksEntry : Route("marks-entry")
    object OnlineClass : Route("online-class")
    object QuestionBank : Route("questions")
    object ProductDetail : Route("product_detail/{id}") {
        fun createRoute(id: Int) = "product_detail/$id"
    }

    object Cart : Route("cart")
}