package com.lsp;

public class JobSeekerImpl implements JobSeeker{
    private String name;

    public JobSeekerImpl(String name) {
        this.name = name;
    }

    @Override
    public void update(String jobOpening) {
        System.out.println(name + ", 新的工作机会: " + jobOpening);
    }
}
