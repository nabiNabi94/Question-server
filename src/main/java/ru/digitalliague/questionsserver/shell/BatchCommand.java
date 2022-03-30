package ru.digitalliague.questionsserver.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.NotNull;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@RequiredArgsConstructor
@ShellComponent
public class BatchCommand {
    private final Job importUserJob;
    private final JobOperator jobOperator;
    private final JobLauncher jobLauncher;

    @ShellMethod(value = "startJobMigration", key = "sm-jo")
    public void startJobMigration() throws Exception {
        Long importQuestionJob = jobOperator.start("importQuestionJob",null);
        System.out.println(jobOperator.getSummary(importQuestionJob));
    }

    @ShellMethod(value = "startJobMigrationLauncher", key = "sm-jl")
    public void startJobMigrationLauncher() throws Exception {
        JobExecution jobExecution = jobLauncher
                .run(importUserJob,
                        new JobParametersBuilder()
                                .addLong("key",
                                        new Date().getTime())
                .toJobParameters());
        System.out.println(jobExecution);
    }


}
