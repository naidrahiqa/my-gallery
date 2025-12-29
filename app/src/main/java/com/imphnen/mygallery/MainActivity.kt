package com.imphnen.mygallery

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.imphnen.mygallery.ui.screens.*
import com.imphnen.mygallery.ui.theme.MyGalleryTheme
import com.imphnen.mygallery.viewmodel.GalleryViewModel

class MainActivity : ComponentActivity() {
    
    private val viewModel: GalleryViewModel by viewModels()
    private var hasPermission by mutableStateOf(false)
    
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasPermission = permissions.values.all { it }
        if (hasPermission) {
            viewModel.loadMedia()
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        requestPermissions()
        
        setContent {
            MyGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (hasPermission) {
                        GalleryApp(viewModel)
                    } else {
                        PermissionRequired(
                            onGrantClick = { requestPermissions() }
                        )
                    }
                }
            }
        }
    }
    
    private fun requestPermissions() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO
            )
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        
        permissionLauncher.launch(permissions)
    }
}

@Composable
fun PermissionRequired(
    onGrantClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Filled.PermMedia,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Storage Permission Required",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We need access to your storage to display your photos and videos.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onGrantClick) {
            Text("Grant Permission")
        }
    }
}

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object AllMedia : Screen("all_media", "All Media", Icons.Filled.PhotoLibrary)
    object Folders : Screen("folders", "Folders", Icons.Filled.Folder)
    object Favorites : Screen("favorites", "Favorites", Icons.Filled.Favorite)
    object Trash : Screen("trash", "Trash", Icons.Filled.Delete)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryApp(viewModel: GalleryViewModel) {
    val navController = rememberNavController()
    val allMedia by viewModel.allMedia.collectAsStateWithLifecycle()
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val sortType by viewModel.sortType.collectAsStateWithLifecycle()
    val sortOrder by viewModel.sortOrder.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    
    LaunchedEffect(Unit) {
        viewModel.loadMedia()
    }
    
    val screens = listOf(
        Screen.AllMedia,
        Screen.Folders,
        Screen.Favorites,
        Screen.Trash
    )
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.AllMedia.route
            ) {
                composable(Screen.AllMedia.route) {
                    AllMediaScreen(
                        items = allMedia,
                        sortType = sortType,
                        sortOrder = sortOrder,
                        onSortTypeChange = { viewModel.setSortType(it) },
                        onSortOrderChange = { viewModel.setSortOrder(it) },
                        onItemClick = { /* TODO: Open detail view */ },
                        onFavoriteClick = { viewModel.toggleFavorite(it) },
                        onDeleteClick = { viewModel.moveToTrash(it) }
                    )
                }
                
                composable(Screen.Folders.route) {
                    FoldersScreen(
                        folders = folders,
                        onFolderClick = { /* TODO: Navigate to folder detail */ }
                    )
                }
                
                composable(Screen.Favorites.route) {
                    FavoritesScreen(
                        items = viewModel.getFavorites(),
                        onItemClick = { /* TODO: Open detail view */ },
                        onFavoriteClick = { viewModel.toggleFavorite(it) },
                        onDeleteClick = { viewModel.moveToTrash(it) }
                    )
                }
                
                composable(Screen.Trash.route) {
                    TrashScreen(
                        items = viewModel.getTrashItems(),
                        onItemClick = { /* TODO: Open detail view */ },
                        onRestoreClick = { viewModel.restoreFromTrash(it) }
                    )
                }
            }
            
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
