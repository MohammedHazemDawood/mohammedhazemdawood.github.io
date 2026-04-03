package com.mhd_07.personal_website

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.zIndex
import com.mhd_07.personal_website.model.Certificate
import com.mhd_07.personal_website.model.Contact
import com.mhd_07.personal_website.model.Event
import com.mhd_07.personal_website.model.Hero
import com.mhd_07.personal_website.model.Project
import com.mhd_07.personal_website.model.fetchCertificates
import com.mhd_07.personal_website.model.fetchContacts
import com.mhd_07.personal_website.model.fetchEvents
import com.mhd_07.personal_website.model.fetchHero
import com.mhd_07.personal_website.model.fetchProjects
import com.mhd_07.personal_website.util.ScreenType
import com.mhd_07.personal_website.components.CertificatesSection
import com.mhd_07.personal_website.components.ContactSection
import com.mhd_07.personal_website.components.EventsSection
import com.mhd_07.personal_website.components.Header
import com.mhd_07.personal_website.components.HomeSection
import com.mhd_07.personal_website.components.ImagePreviewDialog
import com.mhd_07.personal_website.components.ProjectsSection
import com.mhd_07.personal_website.components.Section
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val theme = LocalTheme.current
    var headerSpacing by remember { mutableStateOf(theme.dimensions.sectionSpacing) }
    val density = LocalDensity.current
    val scrollState = rememberLazyListState()
    val currentSection by remember {
        derivedStateOf {
            (scrollState.layoutInfo.visibleItemsInfo.firstOrNull()?.key?.toString()
                ?: Section.Home.id)
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var dialogData by remember { mutableStateOf(emptyList<String>()) }
    var dialogImageIndex by remember { mutableStateOf(0) }
    var events by remember {
        mutableStateOf(emptyList<Event>())
    }
    var projects by remember {
        mutableStateOf(emptyList<Project>())
    }
    var certificates by remember {
        mutableStateOf(emptyList<Certificate>())
    }

    var contacts by remember {
        mutableStateOf(emptyList<Contact>())
    }

    var heroData by remember {
        mutableStateOf(Hero())
    }


    LaunchedEffect(Unit) {

        try {
            heroData = fetchHero()
            events = fetchEvents()
            projects = fetchProjects()
            certificates = fetchCertificates()
            contacts = fetchContacts()

        } catch (e: Throwable) {
//            println("❌ LaunchedEffect CRASHED:$e")
//            e.printStackTrace()
        }
    }
    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize().background(theme.colors.background).pullToRefresh(
            state = pullToRefreshState,
            enabled = theme.screenType == ScreenType.MOBILE,
            onRefresh = {
                isRefreshing = true
                openUrl("")
            },
            isRefreshing = isRefreshing
        ),
        contentAlignment = Alignment.TopCenter,
    ) {
        PullToRefreshDefaults.Indicator(
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            modifier = Modifier.align(Alignment.TopCenter).zIndex(1f)
        )

        LazyColumn(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(theme.dimensions.sectionSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item(key = Section.Home.id) {
                Spacer(modifier = Modifier.height(if (theme.screenType == ScreenType.MOBILE) theme.dimensions.inSectionSpacing else headerSpacing))
                HomeSection(modifier = Modifier.fillMaxWidth(0.8f), data = heroData)
            }//end of Home Section
            item(key = Section.Events.id) {
                EventsSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    data = events,
                    onOpenDialog = { it, index ->
                        dialogData = it
                        dialogImageIndex = index
                    })
            }//end of Events Section
            item(key = Section.Certificates.id) {
                CertificatesSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    data = certificates,
                    onOpenDialog = {
                        dialogData = listOf(it)
                    }
                )
            }//end of Certificates Section
            item(key = Section.Projects.id) {
                ProjectsSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    data = projects,
                    onOpenDialog = { it, index ->
                        dialogData = it
                        dialogImageIndex = index
                    }
                )
            }//end of Projects Section
            item(key = Section.Contact.id) {
                ContactSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    contacts = contacts
                )
                Spacer(modifier = Modifier.height(theme.dimensions.inSectionSpacing))
            }//end of Contact Section
        }
    }
    if (theme.screenType == ScreenType.DESKTOP)
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = theme.dimensions.inSectionSpacing)
                .onGloballyPositioned {
                    val y = it.positionInRoot().y
                    with(density) {
                        headerSpacing =
                            theme.dimensions.inSectionSpacing + y.toDp() + theme.dimensions.sectionSpacing
                    }
                }, contentAlignment = Alignment.Center
        ) {
            Header(
                modifier = Modifier.fillMaxWidth(0.6f),
                onSectionClick = { clicked ->
                    val index = Section.sections.indexOfFirst { it.id == clicked.id }
                    if (index != -1) {
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(index)
                        }
                    }
                },
                currentSection = Section.valueOf(currentSection)
            )
        }


    if (dialogData.isNotEmpty())
        ImagePreviewDialog(
            images = dialogData,
            onDismiss = { dialogData = emptyList() },
            firstImageIndex = dialogImageIndex
        )

}
