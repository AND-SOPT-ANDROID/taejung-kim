package org.sopt.and.presentation.mypage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.home.components.MovieEditorItem
import org.sopt.and.presentation.home.components.MovieList
import org.sopt.and.presentation.mypage.MyViewModel
import org.sopt.and.ui.theme.Typography

@Composable
fun MyMovieInfo(
    viewModel: MyViewModel,
    titleResId: Int,
    imageResId: Int,
    noContentResId: Int
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    // 제목 리소스 ID에 따라 다른 StateFlow를 선택
    val movieList by if (titleResId == R.string.my_watching) {
        // collectAsStateWithLifecycle로 변경
        viewModel.myWatching.collectAsStateWithLifecycle(lifecycleOwner)
    } else
        viewModel.myWatching.collectAsStateWithLifecycle(lifecycleOwner)

    if (movieList.isNotEmpty()) {
        // 데이터가 있는 경우
        // 홈의 MovieList 사용, title이 없는 경우 순위 없는 것
        Text(
            text = stringResource(id = titleResId),
            color = Color.White,
            style = Typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        MovieList("", movieList)
    } else {
        // 데이터가 없는 경우
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = titleResId),
                color = Color.White,
                style = Typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Image(
                painter = painterResource(id = imageResId),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp),
                alignment = Alignment.Center,
                contentDescription = stringResource(id = noContentResId)
            )
            Text(
                text = stringResource(id = noContentResId),
                color = Color.Gray,
                style = Typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

