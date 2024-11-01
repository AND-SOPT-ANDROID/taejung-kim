package org.sopt.and.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.presentation.home.MovieData
import org.sopt.and.ui.theme.Typography

@Composable
fun MovieList(title:String, movieData: List<MovieData>){
    Text(
        text = title,
        style = Typography.bodyLarge,
        color = Color.White
    )
    LazyRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(movieData.size){ index ->
            if(title == stringResource(R.string.home_top_20))
                MovieTopItem(movieData[index])
            else
                MovieEditorItem(movieData[index])
        }
    }
}
