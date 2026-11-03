package com.example.superkalam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme,
                typography = MaterialTheme.typography,
                content = {
                    Surface(color = Color(0xFFF4F5F7), modifier = Modifier.fillMaxSize()) {
                        SuperKalamDashboard()
                    }
                }
            )
        }
    }
}

@Composable
fun SuperKalamDashboard() {
    var selectedSidebar by remember { mutableStateOf("Prelims PYQs") }
    var selectedMainView by remember { mutableStateOf("Practice") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101418))
    ) {
        BrowserTopBar()

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F5F7))
        ) {
            Sidebar(
                selectedItem = selectedSidebar,
                onSelected = {
                    selectedSidebar = it
                    selectedMainView = if (it == "My Progress") "Progress" else "Practice"
                }
            )

            Box(modifier = Modifier.weight(1f)) {
                if (selectedMainView == "Practice") {
                    PracticeScreen()
                } else {
                    ProgressScreen()
                }
            }
        }
    }
}

@Composable
fun BrowserTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(Color(0xFF171B21))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("←", color = Color.White, fontSize = 20.sp)
                Text("→", color = Color(0xFF8B9197), fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF5BBE68))
                )
                Text(
                    "dashboard.superkalam.com/p...",
                    color = Color(0xFFE8EAED),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { idx ->
                    val label = listOf("A", "B", "C", "D", "E")[idx]
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(18.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFF2A3038))
                    ) {
                        Text(label, color = Color.White, fontSize = 9.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
fun Sidebar(selectedItem: String, onSelected: (String) -> Unit) {
    val menuItems = listOf(
        "Home",
        "Ask SuperKalam",
        "Current Affairs",
        "Learn",
        "My Library",
        "MCQ Practice",
        "Prelims PYQs",
        "My Tests",
        "Answer Evaluation",
        "PYQ Practice"
    )

    Column(
        modifier = Modifier
            .width(220.dp)
            .fillMaxHeight()
            .background(Color(0xFFF3F5F7))
            .border(1.dp, Color(0xFFE3E6EA))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 18.dp, bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF1E88E5))
            )
            Text(
                "superkalam",
                color = Color(0xFF1E88E5),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        menuItems.forEach { item ->
            val isSelected = item == selectedItem
            val background = if (isSelected) Color(0xFFE1EAFB) else Color.Transparent
            val textColor = if (isSelected) Color(0xFF1E88E5) else Color(0xFF2F3640)
            val textWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(background)
                    .clickable { onSelected(item) }
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    iconForItem(item),
                    fontSize = 16.sp,
                    modifier = Modifier.width(20.dp)
                )
                Text(
                    item,
                    color = textColor,
                    fontWeight = textWeight,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF17B48B))
            )
            Text(
                " amit kumar",
                color = Color(0xFF1F2430),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun PracticeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 22.dp, end = 22.dp, top = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFB)),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        "Practice PYQs",
                        color = Color(0xFF1B1F24),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier.height(18.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        SelectBox(label = "Year", value = "All Years")
                        SelectBox(label = "Subjects", value = "All Subjects")
                        SelectBox(label = "Topics", value = "Select Topics")
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E78FF))
                    ) {
                        Text("Start Practice", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                modifier = Modifier.width(290.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text("My progress", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B1F24))
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE9EDF0))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("All Subjects", color = Color(0xFF394356), fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFE9EDF0))
                            .padding(horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text("All Years", color = Color(0xFF394356), fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0xFFE5E7EB))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.62f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFF2FBF69))
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("376 / 1600 questions attempted", color = Color(0xFF5C6977), fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(18.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Accuracy ", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1C222A))
                        Text("73.67%", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1AA065))
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF2FBF69))
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text("Quick Practice Set", color = Color(0xFF141B22), fontWeight = FontWeight.Bold, fontSize = 28.sp)

        Spacer(modifier = Modifier.height(18.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            PracticeSetCard(title = "Parliament", count = "48 Questions", accent = Color(0xFFF0DDAA))
            PracticeSetCard(title = "Renewable Energy", count = "19 Questions", accent = Color(0xFFE7DFB7))
            PracticeSetCard(title = "Climate Change Mitigation", count = "40 Questions", accent = Color(0xFFEBDFF0))
            AttemptCard()
        }

        Spacer(modifier = Modifier.height(28.dp))
        Text("How It Works?", color = Color(0xFF1B1E24), fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(modifier = Modifier.height(18.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            items(listOf(
                FeatureItem("Learn & Practice PYQs", "Practice PYQs on any topic or concept - subject-wise or year-wise", Color(0xFFF5EFC8)),
                FeatureItem("Simplified Explanations", "Learn the concept from simplified explanations & get Mindmaps", Color(0xFFE8F6EE)),
                FeatureItem("Discuss your Doubts", "Go deeper into the concept by directly discussing with SuperKalam", Color(0xFFE7F2FF)),
                FeatureItem("Improve & Revise questions", "Revise your bookmarks & incorrect PYQs & improve your concepts", Color(0xFFEAF8ED))
            )) { item ->
                FeatureCard(feature = item)
            }
        }
    }
}

@Composable
fun ProgressScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Quick Practice Set", color = Color(0xFF161C22), fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text("My progress", color = Color(0xFF1E88E5), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            PracticeSetCard(title = "Parliament", count = "48 Questions", accent = Color(0xFFF0DDAA), compact = true)
            PracticeSetCard(title = "Renewable Energy", count = "19 Questions", accent = Color(0xFFE7DFB7), compact = true)
            PracticeSetCard(title = "Climate Change Mitigation", count = "40 Questions", accent = Color(0xFFEBDFF0), compact = true)
            AttemptCard(compact = true)
        }

        Spacer(modifier = Modifier.height(28.dp))
        Text("How It Works?", color = Color(0xFF181D22), fontWeight = FontWeight.Bold, fontSize = 28.sp)

        Spacer(modifier = Modifier.height(18.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(listOf(
                FeatureItem("Learn & Practice PYQs", "Practice PYQs on any topic or concept - subject-wise or year-wise", Color(0xFFF5EFC8)),
                FeatureItem("Simplified Explanations", "Learn the concept from simplified explanations & get Mindmaps", Color(0xFFE8F6EE)),
                FeatureItem("Discuss your Doubts", "Go deeper into the concept by directly discussing with SuperKalam", Color(0xFFE7F2FF)),
                FeatureItem("Improve & Revise questions", "Revise your bookmarks & incorrect PYQs & improve your concepts", Color(0xFFEAF8ED))
            )) { item ->
                FeatureCard(feature = item)
            }
        }
    }
}

@Composable
fun SelectBox(label: String, value: String) {
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(58.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFDFE4EA), RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F8FA))
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(label, color = Color(0xFF5A6574), fontSize = 12.sp)
            Text(value, color = Color(0xFF1F242B), fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun PracticeSetCard(title: String, count: String, accent: Color, compact: Boolean = false) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F9FB)),
        modifier = Modifier
            .width(if (compact) 170.dp else 190.dp)
            .height(if (compact) 110.dp else 150.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(if (compact) 26.dp else 30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(accent)
                    .border(1.dp, Color(0xFFE0D4A6), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("✦", fontSize = if (compact) 12.sp else 14.sp)
            }

            Text(
                title,
                fontSize = if (compact) 16.sp else 20.sp,
                color = Color(0xFF1D232B),
                fontWeight = FontWeight.Bold
            )
            Text(count, fontSize = if (compact) 12.sp else 14.sp, color = Color(0xFF5A6574))
        }
    }
}

@Composable
fun AttemptCard(compact: Boolean = false) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F7FB)),
        modifier = Modifier
            .width(if (compact) 170.dp else 190.dp)
            .height(if (compact) 110.dp else 150.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Attempt", color = Color(0xFF1E76FF), fontSize = if (compact) 14.sp else 16.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    "Full-Length PYQ Tests",
                    color = Color(0xFF1A4A80),
                    fontWeight = FontWeight.Bold,
                    fontSize = if (compact) 14.sp else 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text("Practice Now >", color = Color(0xFF1E76FF), fontSize = if (compact) 12.sp else 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FeatureCard(feature: FeatureItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFB)),
        modifier = Modifier
            .width(290.dp)
            .height(150.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(feature.tint),
                contentAlignment = Alignment.Center
            ) {
                Text("✦", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(18.dp))

            Text(
                feature.title,
                color = Color(0xFF1B1F24),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private data class FeatureItem(
    val title: String,
    val description: String,
    val tint: Color
)

private fun iconForItem(item: String): String = when (item) {
    "Home" -> "⌂"
    "Ask SuperKalam" -> "✦"
    "Current Affairs" -> "◌"
    "Learn" -> "◍"
    "My Library" -> "▣"
    "MCQ Practice" -> "◫"
    "Prelims PYQs" -> "▤"
    "My Tests" -> "▣"
    "Answer Evaluation" -> "◍"
    "PYQ Practice" -> "▤"
    else -> "•"
}
