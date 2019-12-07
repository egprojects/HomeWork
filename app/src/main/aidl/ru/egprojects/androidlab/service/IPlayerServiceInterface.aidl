package ru.egprojects.androidlab.service;

import ru.egprojects.androidlab.model.Audio;

interface IPlayerServiceInterface {

    void play();
    void pause();
    void stop();
    void setCurrentAudioIndex(int index);
    Audio getCurrentAudio();
    void prev();
    void next();
    boolean isPlaying();
    int getPosition();
    void seekTo(int pos);
    int getDuration();
    void release();

}
