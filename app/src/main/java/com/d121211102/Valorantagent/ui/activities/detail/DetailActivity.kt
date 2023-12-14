package com.d121211102.Valorantagent.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211102.Valorantagent.data.models.Ability
import com.d121211102.Valorantagent.data.models.Agent
import com.d121211102.Valorantagent.ui.theme.ValorantagentTheme


    class DetailActivity : ComponentActivity() {

        private var selectedAgent: Agent? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            selectedAgent = intent.getParcelableExtra("AGENT")
            setContent {
                ValorantagentTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        DetailScreen(selectedAgent!!)
                    }
                }
            }
        }
        @Composable
        fun DetailScreen(selectedAgent: Agent) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data( selectedAgent?.displayIcon )
                        .crossfade(true)
                        .build(), contentDescription = selectedAgent?.displayName,
                    modifier = Modifier
                        .width(400.dp)
                        .height(600.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = selectedAgent?.displayName.toString(), style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(selectedAgent?.role?.displayIcon)
                        .crossfade(true)
                        .build(),
                    contentDescription = selectedAgent?.role?.displayName,
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = selectedAgent?.role?.displayName.toString(), style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = selectedAgent?.role?.description.toString(), style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = selectedAgent?.description.toString(), style = MaterialTheme.typography.bodyMedium)


            }
        }

    }
