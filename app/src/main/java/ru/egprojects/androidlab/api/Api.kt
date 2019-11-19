package ru.egprojects.androidlab.api

import ru.egprojects.androidlab.model.Anime

object Api {

    private val screenshotsSrc = listOf(
            "https://static3.statics.life/online/new_screens/big/s1/10817/1/3.mp4.jpg",
            "https://static3.statics.life/online/new_screens/big/s1/10817/6/7.mp4.jpg",
            "https://static3.statics.life/online/new_screens/big/s1/10817/1/5.mp4.jpg"
    )
    private var animes = arrayListOf(
            Anime(
                    "Vinland Saga",
                    "https://static3.statics.life/online/poster/22b73ad57e.jpg",
                    "приключения, драма, исторический",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Shokugeki no Souma: Shin no Sara",
                    "https://static3.statics.life/online/poster/4a81188d73.jpg",
                    "комедия, школа, этти, романтика",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Monkey Turn V",
                    "https://static3.statics.life/online/poster/e2e8a69fb9.jpg",
                    "приключения, спорт",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Kyoukai no Rinne TV-3",
                    "https://static3.statics.life/online/poster/1c9c5fb7de.jpg",
                    "приключения, комедия, мистика, романтика",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Cardfight!! Vanguard",
                    "https://static3.statics.life/online/poster/a8290c9ace.jpg",
                    "приключения, сёнэн",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Null Peta",
                    "https://static3.statics.life/online/poster/b9be8bea07.jpg",
                    "комедия, научная фантастика",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Sword Art Online: Alicization - War of Underworld",
                    "https://static3.statics.life/online/poster/c115d50e02.jpg",
                    "приключения, фэнтези, романтика, драма",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Chihayafuru TV-3",
                    "https://static3.statics.life/online/poster/ebe5db03da.jpg",
                    "школа, спорт, драма, романтика, повседневность",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Enen no Shouboutai",
                    "https://static3.statics.life/online/poster/f646a9cb19.jpg",
                    "приключения, драма, комедия, научная фантастика",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            ),
            Anime(
                    "Ensemble Stars!",
                    "https://static3.statics.life/online/poster/13c4282b32.jpg",
                    "школа, музыкальный, повседневность, сёдзё",
                    "Description...\nDescription...\nDescription...",
                    screenshotsSrc
            )
    )

    fun loadAnimes() = animes

    fun loadAnime(i: Int) = loadAnimes()[i]

    fun add(i: Int, anime: Anime) {
        if (i in animes.indices) {
            animes.add(i, anime)
        } else animes.add(anime)
    }

    fun remove(i: Int) = animes.removeAt(i)
}
