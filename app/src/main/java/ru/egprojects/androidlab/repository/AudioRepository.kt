package ru.egprojects.androidlab.repository

import ru.egprojects.androidlab.R
import ru.egprojects.androidlab.model.Audio

object AudioRepository {
    val data = listOf(
            Audio(
                    "Disturbed",
                    "Watch you burn",
                    R.drawable.poster_disturbed_evolution,
                    "Alternative metal, heavy metal, hard rock",
                    R.raw.audio_disturbed_watch_you_burn
            ),
            Audio(
                    "Saint Asonia feat. Sharon Den Adel",
                    "Sirens",
                    R.drawable.poster_flawed_design,
                    "Alternative metal, post-grunge, nu metal",
                    R.raw.audio_saint_asonia_feat_sharon_den_adel_sirens
            ),
            Audio(
                    "Disturbed",
                    "You're mine",
                    R.drawable.poster_disturbed_immortalized,
                    "Alternative metal, heavy metal",
                    R.raw.audio_disturbed_youre_mine
            ),
            Audio(
                    "Saint Asonia",
                    "Blind",
                    R.drawable.poster_flawed_design,
                    "Alternative metal, post-grunge, nu metal",
                    R.raw.audio_saint_asonia_blind
            ),
            Audio(
                    "Saint Asonia",
                    "This august day",
                    R.drawable.poster_flawed_design,
                    "Alternative metal, post-grunge, nu metal",
                    R.raw.audio_saint_asonia_this_august_day
            ),
            Audio(
                    "Saint Asonia feat. Sully Erna",
                    "The hunted",
                    R.drawable.poster_flawed_design,
                    "Alternative metal, post-grunge, nu metal",
                    R.raw.audio_saint_asonia_feat_sully_erna_the_hunted
            ),
            Audio(
                    "Saint Asonia",
                    "Beast",
                    R.drawable.poster_flawed_design,
                    "Alternative metal, post-grunge, nu metal",
                    R.raw.audio_saint_asonia_beast
            )
    )
}
