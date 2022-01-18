package com.test_sma.workOutGen

import android.graphics.Color.alpha
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.test_sma.R
import com.test_sma.workOutGen.ui.theme.*
import kotlinx.coroutines.launch

class TypeSelect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("userName").toString()
        setContent {
            TEST_SMATheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ScaffoldExample()
                }
            }
        }
    }
}
val condition : Int =0

//modifier = Modifier.padding(24.dp)
@Composable
fun TopLeve(onMenuClicked: () -> Unit){
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "WorkOut App", color = Color.White)
        },
        // Provide the navigation Icon (Icon on the left to toggle drawer)
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier.clickable(onClick = onMenuClicked),
                tint = Color.White
            )
        },
        // background color of topAppBar
        backgroundColor = Color(0xFF0F9D58)
    )
}
@Composable
fun BottomBar() {
    // BottomAppBar Composable
    BottomAppBar(
        backgroundColor = Color(0xFF0F9D58)
    ) {
        Text(text = "Bottom App Bar", color = Color.White)
    }
}

@Composable
fun Drawer() {
    // Column Composable
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        // Repeat is a loop which
        // takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}

@Composable
fun Body() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        testList2()
    }
}

@Preview
@Composable
fun ScaffoldExample() {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of
    // Drawer and snackbar should happen in
    // background thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

    // Scaffold Composable
    Scaffold(

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topbar we created
        topBar = {
            TopLeve(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                })
        },

        // pass the bottomBar
        // we created
        bottomBar = { BottomBar() },

        // Pass the body in
        // content parameter
        content = {

            Body()
        },

        // pass the drawer
        drawerContent = {
            Drawer()
        },

        floatingActionButton = {
            // Create a floating action button in
            // floatingActionButton parameter of scaffold
            FloatingActionButton(

                onClick = {
                    // When clicked open Snackbar
                    coroutineScope.launch {
                        when (scaffoldState.snackbarHostState.showSnackbar(
                            // Message In the snackbar
                            message = "Snack Bar",
                            actionLabel = "Dismiss"
                        )) {
                            SnackbarResult.Dismissed -> {
                                // do something when
                                // snack bar is dismissed
                            }

                            SnackbarResult.ActionPerformed -> {
                                // when it appears
                            }
                        }
                    }
                }) {
                // Simple Text inside FAB
                Text(text = "X")
            }
        }
    )

}
@Composable
fun testList2(){
    var selected by remember { mutableStateOf("Back") }
    var selected2 by remember { mutableStateOf("Biceps") }

    Surface( modifier = Modifier
        .fillMaxSize()

    ) {
        Text(text = "Please select muscle groups(2)!",
        Modifier.padding(horizontal = 24.dp),
        style = Typography.h4)
        Spacer(modifier = Modifier.height(48.dp))
        Row(Modifier.padding(vertical = 100.dp, horizontal = 42.dp)) {
            Column(Modifier.padding(20.dp)) {
                Text(text = "Primary",
                style = Typography.h5)
                Row(Modifier.padding(4.dp)) {
                    RadioButton(selected = selected == "Back" , onClick = { selected = "Back"}, modifier = Modifier.padding(6.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Back",
                        modifier = Modifier
                            .clickable(onClick = { selected = "Back" })
                            .padding(8.dp)
                    )
                }
                Row(Modifier.padding(4.dp)) {
                    RadioButton(selected = selected == "Legs" , onClick = { selected = "Legs"}, modifier = Modifier.padding(6.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Legs",
                        modifier = Modifier
                            .clickable(onClick = { selected = "Legs" })
                            .padding(8.dp)
                    )
                }
                Row(Modifier.padding(4.dp)) {
                    RadioButton(selected = selected == "Abs" , onClick = { selected = "Abs"}, modifier = Modifier.padding(6.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Abs",
                        modifier = Modifier
                            .clickable(onClick = { selected = "Abs" })
                            .padding(8.dp)
                    )
                }
                Row(Modifier.padding(4.dp)) {
                    RadioButton(selected = selected == "Chest" , onClick = { selected = "Chest"}, modifier = Modifier.padding(4.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Chest",
                        modifier = Modifier
                            .clickable(onClick = { selected = "Chest" })
                            .padding(8.dp)
                    )
                }
            }
            Column(Modifier.padding(24.dp)) {
                Text(text = "Secondary",style = Typography.h5)
                Row(Modifier.padding(4.dp)) {
                    RadioButton(selected = selected2 == "Biceps" , onClick = { selected2 = "Abs"}, modifier = Modifier.padding(6.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Biceps",
                        modifier = Modifier
                            .clickable(onClick = { selected2 = "Biceps" })
                            .padding(8.dp)
                    )
                }
                Row(Modifier.padding(4.dp)) {
                    RadioButton(selected = selected2 == "Triceps" , onClick = { selected2 = "Chest"}, modifier = Modifier.padding(6.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Triceps",
                        modifier = Modifier
                            .clickable(onClick = { selected2 = "Triceps" })
                            .padding(8.dp)
                    )
                }

                Row(Modifier.padding(4.dp)){
                    RadioButton(selected = selected2 == "Shoulders" , onClick = { selected2 = "Back"}, modifier = Modifier.padding(6.dp))
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = "Shoulders",
                        modifier = Modifier
                            .clickable(onClick = { selected2 = "Shoulders" })
                            .padding(8.dp)
                    )
                }


            }

        }


    }

}
