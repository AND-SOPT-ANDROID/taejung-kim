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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
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
                    bottomBar = { BottomBar(navController) }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .background(Color.DarkGray)
                            .padding(innerPadding)) {
                        NavGraph(navController = navController,
                            mainViewModel = mainViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavigationItem(R.string.home, R.drawable.ic_home, "home"),
        BottomNavigationItem(R.string.search, R.drawable.ic_search, "search"),
        BottomNavigationItem(R.string.my, R.drawable.ic_my, "my")
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.Gray,
    ){
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = null) },
                label = { Text(stringResource(id = item.label)) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray
                )

            )
        }
    }
}
