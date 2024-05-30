package com.lsp;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Headhunter headhunter = new HeadhunterImpl();

        JobSeeker jobSeeker1 = new JobSeekerImpl("张三");
        JobSeeker jobSeeker2 = new JobSeekerImpl("李四");

        headhunter.register(jobSeeker1);
        headhunter.register(jobSeeker2);

        headhunter.notifyUpdate("软件工程师职位开放");

        headhunter.unregister(jobSeeker1);

        headhunter.notifyUpdate("项目经理职位开放");

    }
}