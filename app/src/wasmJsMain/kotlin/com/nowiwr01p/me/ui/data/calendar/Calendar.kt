package com.nowiwr01p.me.ui.data.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nowiwr01p.me.core_ui.theme.params.colorText
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun Calendar() {
    FlowRow(
        maxItemsInEachRow = 7,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(vertical = 48.dp)
    ) {
        getDatesForCurrentAndNextMonth().take(5).forEach { month -> // TODO: Make ViewPager, getting rid of .take(5)
            month.forEach { date ->
                CalendarIem(date)
            }
        }
    }
}

@Composable
private fun CalendarIem(date: LocalDate?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable {  }
    ) {
        val text = when {
            date != null -> remember(date) {
                val dayOfMonth = date.dayOfMonth.toString().padStart(2, '0')
                val monthNumber =  date.monthNumber.toString().padStart(2, '0')
                "$dayOfMonth.$monthNumber"
            }
            else -> "     "
        }
        Text(
            text = text,
            color = colorText,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp)
        )
    }
}