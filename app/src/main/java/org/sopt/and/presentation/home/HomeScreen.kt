package org.sopt.and.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.sopt.and.R
import org.sopt.and.presentation.home.Component.MovieList
import org.sopt.and.ui.theme.Typography

val movies = listOf(
    MovieData("1", R.drawable.movie1),
    MovieData("2", R.drawable.movie2),
    MovieData("3", R.drawable.movie3),
    MovieData("4", R.drawable.movie4),
    MovieData("5", R.drawable.movie5)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        item { HomeTopBar() }
        stickyHeader {
            HomeCategory()
        }
        item { HomeTopBanner() }
        item {
            HomeEditor()
            Spacer(modifier = Modifier.height(20.dp))
        }
        item{HomeTop20()}
    }
}

@Composable
fun HomeTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ){
        Text(
            text = stringResource(R.string.logo_name),
            fontSize = 30.sp,
            color = Color.White)
        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ){
            Image(
                painter = painterResource(R.drawable.ic_alarm),
                contentDescription = "라이브1")

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(R.drawable.ic_setting),
                contentDescription = "라이브2")
        }
    }
}

@Composable
fun HomeCategory() {
    val textList = listOf(
        stringResource(R.string.home_category_new_classic),
        stringResource(R.string.home_category_drama),
        stringResource(R.string.home_category_entertain),
        stringResource(R.string.home_category_movie),
        stringResource(R.string.home_category_animation),
        stringResource(R.string.home_cateogry_foreign),
        stringResource(R.string.home_category_normal),
        stringResource(R.string.home_category_kids)
    )
    LazyRow(
        modifier = Modifier
            .background(Color.Black)
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(textList.size){ index ->
            Text(text = textList[index],
                fontSize = 16.sp,
                color = Color.LightGray,
                style = Typography.bodyMedium)
        }
    }
}

@Composable
fun HomeTop20() {
    MovieList(stringResource(R.string.home_top_20), movies)
}

@Composable
fun HomeEditor() {
    MovieList(stringResource(R.string.home_trust_editor), movies)
}

@Composable
fun HomeTopBanner() {
    // xml의 viewPager와 유사
    val pagerState = rememberPagerState(
        pageCount = {movies.size}  // 영화 리스트의 크기
    )

    // java의 void와 유사
    LaunchedEffect(Unit) {
        while (isActive) {
            delay(3000)  // 3초마다 페이지 변경
            with(pagerState) {
                // 마지막 페이지까지 가면 다시 처음으로 이동
                val nextPage = if (currentPage == pageCount - 1) 0 else currentPage + 1
                animateScrollToPage(nextPage)  // 해당 페이지로 스크롤 애니메이션
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f),

        ) { page ->
        // 각 페이지에 이미지 표시
        Image(
            painter = painterResource(id = movies[page].img),
            contentDescription = "Top Banner 이미지",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenShow(){
    HomeScreen()
}