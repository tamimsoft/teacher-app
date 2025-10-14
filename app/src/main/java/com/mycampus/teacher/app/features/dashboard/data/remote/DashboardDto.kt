package com.mycampus.teacher.app.features.dashboard.data.remote

import com.mycampus.teacher.app.features.dashboard.ui.Activity
import com.mycampus.teacher.app.features.dashboard.ui.QuickAction
import com.mycampus.teacher.app.features.dashboard.ui.StatItem


data class DashboardDto(val stat: StatItem, val quickAction: QuickAction, val activity: Activity)