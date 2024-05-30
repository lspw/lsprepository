package com.lsp;

public interface Headhunter {
    void register(JobSeeker jobSeeker);
    void unregister(JobSeeker jobSeeker);
    void notifyUpdate(String jobOpening);
}
