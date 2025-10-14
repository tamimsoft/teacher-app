//package com.mycampus.teacher.app.features.auth.ui.component
//
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.launch
//
//@Composable
//fun LoginButton(
//    onLoginClick: () -> Unit,
//    isEnabled: Boolean,
//    isLoading: Boolean
//) {
//    val coroutineScope = rememberCoroutineScope()
//    Button(
//        onClick = {
//            coroutineScope.launch { onLoginClick() }
//        },
//        enabled = isEnabled && !isLoading,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 16.dp)
//            .height(48.dp),
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        if (isLoading) {
//            CircularProgressIndicator(
//                color = MaterialTheme.colorScheme.onPrimary,
//                strokeWidth = 2.dp,
//                modifier = Modifier
//                    .size(20.dp)
//            )
//        } else {
//            Text("Login")
//        }
//    }
//}
