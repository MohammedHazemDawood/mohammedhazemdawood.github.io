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
import androidx.compose.runtime.Composable
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
import com.mhd_07.personal_website.model.Certificate
import com.mhd_07.personal_website.model.Event
import com.mhd_07.personal_website.model.Project
import com.mhd_07.personal_website.util.ScreenType
import components.CertificatesSection
import components.Contact
import components.ContactSection
import components.EventsSection
import components.Header
import components.HomeSection
import components.ProjectsSection
import components.Section
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.github
import personalwebsite.composeapp.generated.resources.gmail
import personalwebsite.composeapp.generated.resources.linkedin
import personalwebsite.composeapp.generated.resources.x

@Composable
fun HomeScreen() {
    val theme = LocalTheme.current
    var headerSpacing by remember { mutableStateOf(theme.dimensions.sectionSpacing) }
    val density = LocalDensity.current
    val scrollState = rememberLazyListState()
    val currentSection by remember {
        derivedStateOf {
            (scrollState.layoutInfo.visibleItemsInfo.firstOrNull()?.key?.toString()
                ?: Section.Home.id).also { println(it) }
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var dialogData by remember { mutableStateOf(emptyList<String>()) }

    Box(
        modifier = Modifier.fillMaxSize().background(theme.colors.background),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(theme.dimensions.sectionSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item(key = Section.Home.id) {
                Spacer(modifier = Modifier.height(if (theme.screenType == ScreenType.MOBILE) theme.dimensions.inSectionSpacing else headerSpacing))
                HomeSection(modifier = Modifier.fillMaxWidth(0.8f))
            }//end of Home Section
            item(key = Section.Events.id) {
                val events = listOf(
                    Event(
                        title = "Basic Sciences Conference 2026",
                        description = "The 10th International Conference of the Faculty of Science, Cairo, Al-Azhar University.",
                        myRole = "Organizer",
                        expandedRole = "I was one of the organizers of this conference, under the supervision of Prof. Mahmoud Al-Hady and the Dean, Prof. Ahmed Sufi. My role included welcoming scientific guests from around the world and assisting with technical tasks.",
                        takeaways = "what i gained: I strengthened my soft skills, communication abilities, and teamwork experience. I also improved my English through real-life interactions with diverse participants, including native speakers, which enhanced my confidence in both professional and social communication.",
                        location = "Cairo, Egypt",
                        images = listOf(
                            "https://raw.githubusercontent.com/MohammedHazemDawood/PersonalWebsite/refs/heads/master/assets/images/img20251020144800.jpg",
                            "https://raw.githubusercontent.com/MohammedHazemDawood/PersonalWebsite/refs/heads/master/assets/images/img20260128122238-1.jpg",
                            "https://raw.githubusercontent.com/MohammedHazemDawood/PersonalWebsite/refs/heads/master/assets/images/img20260128122238.jpg"
                        ),
                        date = "2026-04-04"
                    )
                )
                EventsSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    data = events,
                    onOpenDialog = {
                        dialogData = it
                    })
            }//end of Events Section
            item(key = Section.Certificates.id) {
                val certificates = listOf(
                    Certificate(
                        title = "Android Development with Kotlin",
                        issuer = "Google / Coursera",
                        description = "Professional certificate covering Android basics, Kotlin, and modern UI development with Compose.",
                        date = "2023-10-15",
                        image = "https://example.com/cert-image.jpg",
                        url = "https://coursera.org/verify/example"
                    )
                )
                CertificatesSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    data = certificates,
                    onOpenDialog = {
                        dialogData = listOf(it)
                    }
                )

            }//end of Certificates Section
            item(key = Section.Projects.id) {
                val projects = listOf(
                    Project(
                        title = "Blood Sugar Tracker",
                        description = "",
                        longDescription = "Building a diabetes management Android app using Kotlin/MVI/Room enabling blood sugar logging, and exportable health reports; UI fully designed in Jetpack Compose from Figma prototypes",
                        techStack = listOf(
                            "Jetpack Compose",
                            "Kotlin",
                            "Datastore Preferences",
                            "MVI",
                            "Room Database",
                            "Koin (DI)",
                            "Coroutines",
                            "Figma"
                        ),
                        images = listOf("https://example.com/cert-image.jpg"),
                        githubUrl = "https://github.com/MohammedHazemDawood/BloodSugar",
                        liveUrl = "",
                        date = "",
                        status = "Present",
                    )
                )
                ProjectsSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    data = projects,
                    onOpenDialog = {
                        dialogData = it
                    }
                )
            }//end of Projects Section
            item(key = Section.Contact.id) {
                val contact = listOf(
                    Contact(
                        title = "Email",
                        link = "mohammed.hazem.dawood@gmail.com",
                        icon = painterResource(Res.drawable.gmail)
                    ),
                    Contact(
                        title = "Twitter",
                        link = "https://twitter.com/_mhd_07",
                        icon = painterResource(Res.drawable.x)
                    ),
                    //linked in
                    Contact(
                        title = "LinkedIn",
                        link = "https://linkedin.com/in/mhd07",
                        icon = painterResource(Res.drawable.linkedin)
                    ),
                    //github
                    Contact(
                        title = "Github",
                        link = "https://github.com/MohammedHazemDawood",
                        icon = painterResource(Res.drawable.github)
                    )
                )
                ContactSection(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    contacts = contact
                )
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
}
