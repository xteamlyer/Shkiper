package com.jobik.shkiper.screens.NoteListScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jobik.shkiper.screens.NoteListScreen.NoteListCalendarContent.CalendarViewModel
import com.jobik.shkiper.screens.NoteListScreen.NoteListCalendarContent.NoteListScreenCalendarContent
import com.jobik.shkiper.screens.NoteListScreen.NoteListScreenContent.NoteListScreenContent
import com.jobik.shkiper.ui.theme.CustomTheme
import com.jobik.shkiper.viewmodels.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(navController: NavController) {
    val pagerState = rememberPagerState { 2 }
    val scope = rememberCoroutineScope()

    ReturnUserToMainContent(pagerState, scope)
    ChangeStatusBarColor(pagerState)

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        state = pagerState,
        pageSpacing = 0.dp,
        userScrollEnabled = true,
        reverseLayout = false,
        contentPadding = PaddingValues(0.dp),
        beyondBoundsPageCount = 0,
        pageSize = PageSize.Fill,
    ) {
        if (it == 0) {
            NoteListScreenContent(
                navController = navController,
                viewModel = hiltViewModel<NotesViewModel>(),
                onSlideNext = {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                })
        } else {
            NoteListScreenCalendarContent(
                navController = navController,
                viewModel = hiltViewModel<CalendarViewModel>(),
                onSlideBack = {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChangeStatusBarColor(pagerState: PagerState) {
    val systemUiController = rememberSystemUiController()
    val statusBarActiveColor = CustomTheme.colors.secondaryBackground
    val statusBarDisabledColor = CustomTheme.colors.mainBackground

    LaunchedEffect(pagerState.currentPage) {
        when (pagerState.currentPage) {
            0 -> systemUiController.setStatusBarColor(statusBarDisabledColor)
            1 -> systemUiController.setStatusBarColor(statusBarActiveColor)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ReturnUserToMainContent(
    pagerState: PagerState,
    scope: CoroutineScope
) {
    BackHandler(enabled = pagerState.currentPage == 1) {
        scope.launch {
            pagerState.animateScrollToPage(0)
        }
    }
}