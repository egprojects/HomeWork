package ru.egprojects.androidlab

object Api {
    fun loadAnimes() = arrayListOf(
            Anime(
                    "Vinland Saga",
                    "https://static3.statics.life/online/poster/22b73ad57e.jpg",
                    "приключения, драма, исторический",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Shokugeki no Souma: Shin no Sara",
                    "https://static3.statics.life/online/poster/4a81188d73.jpg",
                    "комедия, школа, этти, романтика",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Monkey Turn V",
                    "https://static3.statics.life/online/poster/e2e8a69fb9.jpg",
                    "приключения, спорт",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Kyoukai no Rinne TV-3",
                    "https://static3.statics.life/online/poster/1c9c5fb7de.jpg",
                    "приключения, комедия, мистика, романтика",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Cardfight!! Vanguard",
                    "https://static3.statics.life/online/poster/a8290c9ace.jpg",
                    "приключения, сёнэн",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Null Peta",
                    "https://static3.statics.life/online/poster/b9be8bea07.jpg",
                    "комедия, научная фантастика",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Sword Art Online: Alicization - War of Underworld",
                    "https://static3.statics.life/online/poster/c115d50e02.jpg",
                    "приключения, фэнтези, романтика, драма",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Chihayafuru TV-3",
                    "https://static3.statics.life/online/poster/ebe5db03da.jpg",
                    "школа, спорт, драма, романтика, повседневность",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Enen no Shouboutai",
                    "https://static3.statics.life/online/poster/f646a9cb19.jpg",
                    "приключения, драма, комедия, научная фантастика",
                    "Description...\nDescription...\nDescription..."
            ),
            Anime(
                    "Ensemble Stars!",
                    "https://static3.statics.life/online/poster/13c4282b32.jpg",
                    "школа, музыкальный, повседневность, сёдзё",
                    "Description...\nDescription...\nDescription..."
            )
    )

    fun loadAnime(id: Int) = loadAnimes()[id]
}
