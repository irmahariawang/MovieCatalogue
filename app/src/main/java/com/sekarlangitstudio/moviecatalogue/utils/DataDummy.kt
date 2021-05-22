package com.sekarlangitstudio.moviecatalogue.utils

import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.MovieResponse
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.TelevisionResponse

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "m01",
                "Below Zero",
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                "Jan 29, 2021 (US)",
                "65%",
                "Action, Crime, Thriller",
                "1 hours, 46 minutes",
                "Lluis Quilez",
                "Javier Gutierrez (as Martin), Karra Elejalde (as Miguel)",
                "m01_below_zero"
            )
        )

        movies.add(
            MovieEntity(
                "m02",
                "Wonder Woman 1984",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "Dec 25, 2020 (US)",
                "70%",
                "Fantasy, Action, Adventure",
                "2 hours, 31 minutes",
                "Patty Jenkins",
                "Gal Gadot (as Diana Prince/Wonder Woman), Chris Pine (as Steve Trevor)",
                "m02_wonder_woman_1984"
            )
        )

        movies.add(
            MovieEntity(
                "m03",
                "The Little Things",
                "Deputy Sheriff Joe \"Deke\" Deacon joins forces with Sgt. Jim Baxter to search for a serial killer who's terrorizing Los Angeles. As they track the culprit, Baxter is unaware that the investigation is dredging up echoes of Deke's past, uncovering disturbing secrets that could threaten more than his case.",
                "Jan 27, 2021 (US)",
                "63%",
                "Thriller, Crime",
                "2 hours, 8 minutes",
                "John Lee Hancock",
                "Denzel Washington (as Joe Deacon), Rami Malek (as Jim Baxter)",
                "m03_the_little_things"
            )
        )

        movies.add(
            MovieEntity(
                "m04",
                "Outside The Wire",
                "In the near future, a drone pilot is sent into a deadly militarized zone and must work with an android officer to locate a doomsday device.",
                "Jan 15, 2021 (US)",
                "65%",
                "Thriller, Action, Science Fiction",
                "1 hours, 54 minutes",
                "Mikael Hafstrom",
                "Anthony Mackie (as Leo), Damson Idris (as Thomas Harp)",
                "m04_outside_the_wire"
            )
        )

        movies.add(
            MovieEntity(
                "m05",
                "Black Water: Abbys",
                "An adventure-loving couple convince their friends to explore a remote, uncharted cave system in the forests of Northern Australia. With a tropical storm approaching, they abseil into the mouth of the cave, but when the caves start to flood, tensions rise as oxygen levels fall and the friends find themselves trapped. Unknown to them, the storm has also brought in a pack of dangerous and hungry crocodiles.",
                "Jul 8, 2020 (US)",
                "51%",
                "Horror, Thriller, Adventure",
                "1 hours, 38 minutes",
                "Andrew Traucki",
                "Jessica McNamee (as Jennifer), Anthony J. Sharpe (as Cash)",
                "m05_black_water_abbys"
            )
        )

        movies.add(
            MovieEntity(
                "m06",
                "Breach",
                "Deep in space they are not alone. A hardened mechanic must stay awake and maintain an interstellar ark fleeing the dying planet Earth with a few thousand lucky souls on board... the last of humanity. Unfortunately, humans are not the only passengers. A shapeshifting alien creature has taken residence, its only goal is to kill as many people as possible. The crew must think quickly to stop this menace before it destroys mankind.",
                "Dec 12, 2020 (US)",
                "46%",
                "Science Fiction, Action",
                "1 hours, 32 minutes",
                "John Suits",
                "Bruce Willis (as Clay Young), Thomas Jane (as Admiral Adams-King)",
                "m06_breach"
            )
        )

        movies.add(
            MovieEntity(
                "m07",
                "Skylines",
                "To save our world she must invade theirs. When a virus threatens to turn the now earth-dwelling friendly alien hybrids against humans, Captain Rose Corley must lead a team of elite mercenaries on a mission to the alien world in order to save what's left of humanity.",
                "Oct 25, 2020 (US)",
                "57%",
                "Science Fiction, Action",
                "1 hours, 53 minutes",
                "Liam O'Donnell",
                "James Cosmo (as Grant), Alexander Siddig (as General Radford)",
                "m07_skylines"
            )
        )

        movies.add(
            MovieEntity(
                "m08",
                "Vanguard",
                "Covert security company Vanguard is the last hope of survival for an accountant after he is targeted by the world's deadliest mercenary organization.",
                "Feb 11, 2021 (US)",
                "65%",
                "Action, Adventure, Crime",
                "1 hours, 47 minutes",
                "Stanley Tong",
                "Jackie Chan (as Tang Huanting), Yang Yang (as Lei Zhenyu)",
                "m08_vanguard"
            )
        )

        movies.add(
            MovieEntity(
                "m09",
                "Finding Ohana",
                "Home is where 'Ohana is. Two Brooklyn siblings' summer in a rural Oahu town takes an exciting turn when a journal pointing to long-lost treasure sets them on an adventure, leading them to reconnect with their Hawaiian heritage.",
                "Jan 29, 2021 (US)",
                "69%",
                "Action, Adventure, Comedy, Family",
                "2 hours, 3 minutes",
                "Jude Weng",
                "Kea Peahu (as Pili), Alex Aiono (as Loane)",
                "m09_finding_ohana"
            )
        )

        movies.add(
            MovieEntity(
                "m10",
                "Tenet",
                "Time runs out. Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "Aug 22, 2020 (US)",
                "73%",
                "Action, Thriller, Science Fiction",
                "2 hours, 30 minutes",
                "Christoper Nolan",
                "John David (as The Protagonist), Robert Pattinson (as Neil)",
                "m10_tenet"
            )
        )

        movies.add(
            MovieEntity(
                "m11",
                "Soul",
                "Is all this living really worth dying for?. Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "Dec 25, 2020 (US)",
                "83%",
                "Family, Animation, Comedy, Drama, Music",
                "1 hours, 42 minutes",
                "Pete Docter",
                "Jamie Foxx (Joe Gardner -voice), Tina Fey (22 -voice)",
                "m11_soul"
            )
        )
        return movies
    }

    fun generateDummyTelevisions(): List<TelevisionEntity> {
        val televisions = ArrayList<TelevisionEntity>()

        televisions.add(
            TelevisionEntity(
                "t01",
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "Jan 15, 2021",
                "84%",
                "Sci-Fi & Fantasy, Mystery, Drama",
                "36 minutes",
                "Jac Schaeffer",
                "Elizabeth Olsen (as Wanda Maximoff/Scarlet Witch), Paul Bettany (as Vision)",
                "t01_wanda_vision"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t02",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Jan 20, 2021",
                "86%",
                "Mystery, Drama, Crime",
                "45 minutes",
                "Roberto Aguirrre-Sacasa",
                "K.J.Apa (as Archie Andrews), Lili Reinhart (as Elizabeth Cooper)",
                "t02_riverdale"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t03",
                "Legacies",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
                "Oct 25, 2018",
                "86%",
                "Sci-Fi & Fantasy, Drama",
                "43 minutes",
                "Julie Plec",
                "Danielle Rose Russell (as Hope Mikaelson), Aria Shahghasemi (as Landon Kirby)",
                "t03_legacies"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t04",
                "Marvel Studios: Legends",
                "As the universe expands, explore the stories of those destined to become legends. Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.",
                "Jan 8, 2021",
                "76%",
                "Documentary",
                "8 minutes",
                "-",
                "Paul Bettany (as Vision), Elizabeth Olsen (as Wanda Maximoff)",
                "t04_legends"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t05",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "2020",
                "86%",
                "Drama",
                "43 minutes",
                "David Shore",
                "Freddie Highmore (as Shaun Murphy), Antonia Thomas (as Claire Browne)",
                "t05_the_good_doctor"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t06",
                "Daughter From Another Mother",
                "After realizing their babies were exchanged at birth, two women develop a plan to adjust to their new lives: creating a single —and peculiar— family.",
                "2021",
                "76%",
                "Comedy, Drama",
                "40 minutes",
                "Carolina Rivera",
                "Ludwika Paleta (as Ana), Paulina Goto (as Mariana)",
                "t06_daughter_from_another_mother"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t07",
                "Grey's Anatomy",
                "The life you save may be your own. Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "2005",
                "82%",
                "Comedy, Drama",
                "43 minutes",
                "Shonda Rhimes",
                "Ellen Pompeo (as Meredith Grey), Justin Chambers (as Alex Karev)",
                "t07_greys_anatomy"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t08",
                "The Mandalorian",
                "Bounty hunting is a complicated profession. After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                "2019",
                "85%",
                "Sci-Fi & Fantasy, Action, Adventure",
                "35 minutes",
                "Jon Favreau",
                "Pedro Pascal (as The Mandalorian/Din Djarin), Gina Carano (as Cara Dune)",
                "t08_the_mandalorian"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t09",
                "Cobra Kai",
                "Cobra Kai never dies. This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "May 22, 2018",
                "81%",
                "Action, Adventure, Drama",
                "40 minutes",
                "Hayden Schlossberg",
                "Ralph Macchio (as Daniel LaRusso), William Zabka (as Johnny Lawrence)",
                "t09_cobra_kai"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t10",
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                "Mar 03, 2013",
                "80%",
                "Action, Adventure, Drama",
                "44 minutes",
                "Michael Hirst",
                "Katheryn Winnick (as Lagertha Lothbrok), Peter Franzen (as King Harald Finehair)",
                "t10_vikings"
            )
        )

        televisions.add(
            TelevisionEntity(
                "t11",
                "Game Of Thrones",
                "Winter Is Coming. Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Apr 17, 2011",
                "84%",
                "Sci-Fi & Fantasy, Action, Drama",
                "1 hour",
                "David Benioff",
                "Emilia Clarke (as Daenerys Targaryen), Kit Harington (as Jon Snow)",
                "t11_game_of_throne"
            )
        )
        return televisions
    }

    fun generateRemoteDummyMovie(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        movies.add(
            MovieResponse(
                "m01",
                "Below Zero",
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                "Jan 29, 2021 (US)",
                "65%",
                "Action, Crime, Thriller",
                "1 hours, 46 minutes",
                "Lluis Quilez",
                "Javier Gutierrez (as Martin), Karra Elejalde (as Miguel)",
                "m01_below_zero"
            )
        )

        movies.add(
            MovieResponse(
                "m02",
                "Wonder Woman 1984",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "Dec 25, 2020 (US)",
                "70%",
                "Fantasy, Action, Adventure",
                "2 hours, 31 minutes",
                "Patty Jenkins",
                "Gal Gadot (as Diana Prince/Wonder Woman), Chris Pine (as Steve Trevor)",
                "m02_wonder_woman_1984"
            )
        )

        movies.add(
            MovieResponse(
                "m03",
                "The Little Things",
                "Deputy Sheriff Joe \"Deke\" Deacon joins forces with Sgt. Jim Baxter to search for a serial killer who's terrorizing Los Angeles. As they track the culprit, Baxter is unaware that the investigation is dredging up echoes of Deke's past, uncovering disturbing secrets that could threaten more than his case.",
                "Jan 27, 2021 (US)",
                "63%",
                "Thriller, Crime",
                "2 hours, 8 minutes",
                "John Lee Hancock",
                "Denzel Washington (as Joe Deacon), Rami Malek (as Jim Baxter)",
                "m03_the_little_things"
            )
        )
        return movies
    }


    fun generateRemoteDummyTv(): List<TelevisionResponse> {
        val televisions = ArrayList<TelevisionResponse>()

        televisions.add(
            TelevisionResponse(
                "t01",
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                "Jan 15, 2021",
                "84%",
                "Sci-Fi & Fantasy, Mystery, Drama",
                "36 minutes",
                "Jac Schaeffer",
                "Elizabeth Olsen (as Wanda Maximoff/Scarlet Witch), Paul Bettany (as Vision)",
                "t01_wanda_vision"
            )
        )

        televisions.add(
            TelevisionResponse(
                "t02",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Jan 20, 2021",
                "86%",
                "Mystery, Drama, Crime",
                "45 minutes",
                "Roberto Aguirrre-Sacasa",
                "K.J.Apa (as Archie Andrews), Lili Reinhart (as Elizabeth Cooper)",
                "t02_riverdale"
            )
        )

        televisions.add(
            TelevisionResponse(
                "t03",
                "Legacies",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
                "Oct 25, 2018",
                "86%",
                "Sci-Fi & Fantasy, Drama",
                "43 minutes",
                "Julie Plec",
                "Danielle Rose Russell (as Hope Mikaelson), Aria Shahghasemi (as Landon Kirby)",
                "t03_legacies"
            )
        )
        return televisions
    }

}