package com.lsp;

import java.util.ArrayList;
import java.util.List;

public class HeadhunterImpl implements Headhunter{
    private List<JobSeeker> jobSeekers;
    public HeadhunterImpl() {
        jobSeekers = new ArrayList<>();
    }
    @Override
    public void register(JobSeeker jobSeeker) {
        jobSeekers.add(jobSeeker);
    }
    @Override
    public void unregister(JobSeeker jobSeeker) {
        jobSeekers.remove(jobSeeker);
    }
    @Override
    public void notifyUpdate(String jobOpening) {
        for (JobSeeker jobSeeker : jobSeekers) {
            jobSeeker.update(jobOpening);
        }
    }
}
