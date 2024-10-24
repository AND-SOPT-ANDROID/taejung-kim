package org.sopt.and

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch
import org.sopt.and.screens.HomeScreen
import org.sopt.and.screens.MyScreen
import org.sopt.and.screens.SearchScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

private var id : String? = ""

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel by viewModels()

        // viewModel에 id 설정
        id = intent.getStringExtra("id")
        Log.d("id",id.toString())
        mainViewModel.setId(id)

        setContent {
            ANDANDROIDTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { AppBottomNavigation(navController) }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") {
                            HomeScreen(innerPadding)
                        }
                        composable("Search") {
                            SearchScreen(innerPadding)
                        }
                        composable("My") {
                            MyScreen(innerPadding, mainViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController) {
    // navController의 백 스택 앤트리를 State 객체로 반환, 스택 변화마다 업데이트
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // 앤트리에서 추출한 현재 네비게이션 Route
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.Gray
    ){
        // 각 탭의 item을 정의, 선택될 때마다 반영
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResource),
                        contentDescription = item.label,
                        tint = if (isSelected) item.selectedColor else item.unselectedColor
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (isSelected) item.selectedColor else item.unselectedColor
                    )
                },
                selected = isSelected,
                onClick = {
                    if (navController.currentDestination?.route != item.route) {
                        navController.navigate(item.route)
                    }
                }
            )
        }
    }
}

// 해당 클래스를 상속 받는 bottomNavItem은 해당 엑티비티에서만 사용
sealed class BottomNavItem(
    val route: String,
    val iconResource: Int,
    val label: String,
    val selectedColor: Color,
    val unselectedColor: Color
) {
    object Home : BottomNavItem("Home", R.drawable.ic_home, "Home", Color.White, Color.Gray)
    object Search : BottomNavItem("Search", R.drawable.ic_search, "Search", Color.White, Color.Gray)
    object Profile : BottomNavItem("My", R.drawable.ic_my, "Profile", Color.White, Color.Gray)
}
