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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import org.sopt.and.presentation.main.MainViewModel
import org.sopt.and.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val movies by viewModel.movies.collectAsState()
    val categories by viewModel.categories.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        item { HomeTopBar() }
        stickyHeader {
            HomeCategory(categories)
        }
        item { HomeTopBanner(movies) }
        item{
            HomeTop20(movies)
            Spacer(modifier = Modifier.height(30.dp))
        }
        item { HomeEditor(movies) }
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
fun HomeCategory(categories: List<Int>) {
    LazyRow(
        modifier = Modifier
            .background(Color.Black)
            .padding(vertical = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(categories.size){ index ->
            Text(text = stringResource(categories[index]),
                fontSize = 20.sp,
                color = Color.LightGray,
                style = Typography.bodyMedium)
        }
    }
}

@Composable
fun HomeTop20(movies: List<MovieData>) {
    MovieList(stringResource(R.string.home_top_20), movies)
}

@Composable
fun HomeEditor(movies: List<MovieData>) {
    MovieList(stringResource(R.string.home_trust_editor), movies)
}

@Composable
fun HomeTopBanner(movies: List<MovieData>) {
    // 실제 페이지 수는 movies.size, 무한 스크롤을 위해 임의의 정수 사용
    val actualPageCount = movies.size
    val infinitePageCount = 100
    val initialPageIndex = infinitePageCount / 2 // 양쪽 무한 스크롤을 위해 가운데 값으로 선정

    val pagerState = rememberPagerState(
        initialPage = initialPageIndex,
        pageCount = { infinitePageCount }
    )

    LaunchedEffect(Unit) {
        while (isActive) {
            delay(3000)  // 3초마다 페이지 변경
            with(pagerState) {
                val nextPage = currentPage + 1
                animateScrollToPage(nextPage)
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
        ) { page ->
            // 현재 페이지의 나머지 값으로 index 설정
            val movieIndex = page % actualPageCount
            Image(
                painter = painterResource(id = movies[movieIndex].img),
                contentDescription = "Top Banner 이미지",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 50.dp)
        ) {
            Text(
                text = "${pagerState.currentPage % actualPageCount + 1} / $actualPageCount",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .background(Color.Black)
                    .padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenShow(){
    HomeScreen(viewModel = MainViewModel())
}