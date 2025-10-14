package com.mycampus.teacher.app.features.auth.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycampus.teacher.BuildConfig
import com.mycampus.teacher.app.features.auth.data.local.SchoolEntity
import kotlinx.coroutines.delay

private const val DEBOUNCE_DELAY_MS = 300L
private const val MAX_RECENT_SCHOOLS = 5
private const val MAX_DROPDOWN_HEIGHT_DP = 400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolBottomSheetDropdown(
	modifier: Modifier = Modifier,
	label: String = "Select School",
	icon: ImageVector,
	items: List<SchoolEntity>,
	selectedItem: SchoolEntity,
	onItemSelected: (SchoolEntity) -> Unit,
	onScrollUp: () -> Unit
) {
	var showSheet by remember { mutableStateOf(false) }
	var searchQuery by rememberSaveable { mutableStateOf("") }
	var debouncedQuery by remember { mutableStateOf(searchQuery) }
	val recentSchools = remember { mutableStateListOf<SchoolEntity>() }
	val focusManager = LocalFocusManager.current
	
	
	// Debounce search query
	LaunchedEffect(searchQuery) {
		delay(DEBOUNCE_DELAY_MS)
		debouncedQuery = searchQuery
	}
	
	// Filter items safely
	val filteredItems by remember(debouncedQuery, items) {
		derivedStateOf { filterSchools(items, debouncedQuery) }
	}
	
	Log.d("Dropdown", "debouncedQuery='$debouncedQuery', filteredItems=${filteredItems.size}")
	
	
	SchoolTextField(
		label = label,
		icon = icon,
		selectedItem = selectedItem,
		onTriggerClick = { showSheet = true },
		modifier = modifier
	)
	
	if (showSheet) {
		SchoolBottomSheet(
			searchQuery = searchQuery,
			onSearchQueryChange = { searchQuery = it },
			filteredItems = filteredItems,
			recentSchools = recentSchools.take(MAX_RECENT_SCHOOLS),
			selectedItem = selectedItem,
			showRecents = debouncedQuery.isEmpty(),
			onSchoolSelected = { school ->
				searchQuery = ""
				onItemSelected(school)
				recentSchools.addRecent(school)
				showSheet = false
				focusManager.clearFocus()
			},
			onScrollUp = onScrollUp,
			onDismiss = { showSheet = false })
	}
}

@Composable
private fun SchoolTextField(
	label: String,
	icon: ImageVector,
	selectedItem: SchoolEntity,
	onTriggerClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	Surface(
		modifier = modifier
			.fillMaxWidth()
			.clickable(onClick = onTriggerClick),
		shape = MaterialTheme.shapes.small,
		border = androidx.compose.foundation.BorderStroke(
			width = 1.dp, color = MaterialTheme.colorScheme.outline
		)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)
			) {
				SchoolIconOrLogo(
					icon = icon, logoUrl = selectedItem.logoUrl, shortName = selectedItem.shortName
				)
				
				Spacer(modifier = Modifier.size(12.dp))
				
				
				
				Column(modifier=modifier.padding(0.dp)) {
					Text(
						text = if (selectedItem.shortName == "") label else selectedItem.shortName,
						style = if (selectedItem.shortName == "") MaterialTheme.typography.bodyLarge else MaterialTheme.typography.titleMedium,
						color = MaterialTheme.colorScheme.onSurface,
						overflow = TextOverflow.Ellipsis,
						maxLines = 1,
					)
					if (selectedItem.shortName != "") {
						Text(
							text = selectedItem.name,
							style = MaterialTheme.typography.bodySmall,
							overflow = TextOverflow.Ellipsis,
							maxLines = 1,
						)
					}
				}
				
			}
			
			Icon(
				Icons.Default.KeyboardArrowDown,
				contentDescription = "Open school selector",
				tint = MaterialTheme.colorScheme.onSurfaceVariant
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SchoolBottomSheet(
	searchQuery: String,
	onSearchQueryChange: (String) -> Unit,
	filteredItems: List<SchoolEntity>,
	recentSchools: List<SchoolEntity>,
	selectedItem: SchoolEntity?,
	showRecents: Boolean,
	onSchoolSelected: (SchoolEntity) -> Unit,
	onScrollUp: () -> Unit,
	onDismiss: () -> Unit
) {
	ModalBottomSheet(onDismissRequest = onDismiss) {
		Column(modifier = Modifier.fillMaxWidth()) {
			OutlinedTextField(
				value = searchQuery,
				onValueChange = onSearchQueryChange,
				label = { Text("Search School") },
				singleLine = true,
				modifier = Modifier
					.fillMaxWidth()
					.padding(16.dp)
			)
			
			Spacer(modifier = Modifier.height(8.dp))
			
			SchoolList(
				filteredItems = filteredItems,
				recentSchools = recentSchools,
				selectedItem = selectedItem,
				showRecents = showRecents,
				onSchoolClick = onSchoolSelected,
				onScrollUp = onScrollUp
			)
		}
	}
}

@Composable
private fun SchoolList(
	filteredItems: List<SchoolEntity>,
	recentSchools: List<SchoolEntity>,
	selectedItem: SchoolEntity?,
	showRecents: Boolean,
	onSchoolClick: (SchoolEntity) -> Unit,
	onScrollUp: () -> Unit
) {
	val listState = rememberLazyListState()
	
	// Proper scroll detection using snapshotFlow
	LaunchedEffect(listState) {
		snapshotFlow { listState.firstVisibleItemIndex }.collect { index ->
			if (index == 0) {
				onScrollUp()
			}
		}
	}
	
	LazyColumn(
		state = listState,
		modifier = Modifier
			.fillMaxWidth()
			.heightIn(max = MAX_DROPDOWN_HEIGHT_DP.dp),
		verticalArrangement = Arrangement.spacedBy(2.dp)
	) {
		if (showRecents && recentSchools.isNotEmpty()) {
			item {
				Text(
					"Recently Selected",
					style = MaterialTheme.typography.titleMedium,
					modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
				)
			}
			items(
				items = recentSchools, key = { "recent_school_${it.id}" }) { school ->
				SchoolDropdownItem(
					school = school,
					isSelected = selectedItem?.id == school.id,
					onClick = { onSchoolClick(school) })
			}
			item {
				HorizontalDivider(
					modifier = Modifier.padding(vertical = 8.dp),
					thickness = DividerDefaults.Thickness,
					color = DividerDefaults.color
				)
			}
		}
		
		if (filteredItems.isEmpty()) {
			item {
				Text(
					"No schools found",
					color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
					modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp)
				)
			}
		} else {
			items(
				items = filteredItems, key = { "filtered_school_${it.id}" }) { school ->
				SchoolDropdownItem(
					school = school,
					isSelected = selectedItem?.id == school.id,
					onClick = { onSchoolClick(school) })
			}
		}
	}
}

@Composable
private fun SchoolDropdownItem(
	school: SchoolEntity, isSelected: Boolean, onClick: () -> Unit
) {
	val backgroundColor = if (isSelected) {
		MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
	} else {
		Color.Transparent
	}
	
	val textColor = if (isSelected) {
		MaterialTheme.colorScheme.primary
	} else {
		MaterialTheme.colorScheme.onSurface
	}
	
	DropdownMenuItem(
		text = {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.background(backgroundColor)
					.padding(vertical = 4.dp)
			) {
				AsyncImage(
					model = school.logoUrl.toFullUrl(),
					contentDescription = school.shortName,
					modifier = Modifier.size(36.dp),
					contentScale = ContentScale.Crop
				)
				
				Spacer(Modifier.size(12.dp))
				
				Column {
					Text(
						text = school.shortName,
						style = MaterialTheme.typography.titleMedium,
						overflow = TextOverflow.Ellipsis,
						maxLines = 1,
						color = textColor
					)
					Text(
						text = school.name,
						style = MaterialTheme.typography.bodySmall,
						overflow = TextOverflow.Ellipsis,
						maxLines = 1,
						color = textColor
					)
				}
			}
		}, onClick = onClick
	)
}

@Composable
private fun SchoolIconOrLogo(
	icon: ImageVector, logoUrl: String?, shortName: String?
) {
	if (!logoUrl.isNullOrBlank()) {
		AsyncImage(
			model = logoUrl.toFullUrl(),
			contentDescription = shortName,
			modifier = Modifier.size(24.dp),
			contentScale = ContentScale.Crop
		)
	} else {
		Icon(icon, contentDescription = null)
	}
}

// Helper functions
private fun filterSchools(schools: List<SchoolEntity>, query: String): List<SchoolEntity> {
	val normalizedQuery = query.trim().lowercase()
	return if (normalizedQuery.isEmpty()) {
		schools
	} else {
		schools.filter { school ->
			school.name.trim().lowercase().contains(normalizedQuery) || school.shortName.trim()
				.lowercase().contains(normalizedQuery)
		}
	}
}

private fun MutableList<SchoolEntity>.addRecent(school: SchoolEntity) {
	remove(school)
	add(0, school)
	if (size > MAX_RECENT_SCHOOLS) {
		removeAt(MAX_RECENT_SCHOOLS)
	}
}

private fun String.toFullUrl(): String = "${BuildConfig.HOST}/$this"