package ru.digitalliague.questionsserver.configuration;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.HibernateCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.digitalliague.questionsserver.entity.Question;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class JobConfig {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final SessionFactory sessionFactory;

    @Bean
    public HibernateCursorItemReader<Question> hibernateReader(){
        return new HibernateCursorItemReaderBuilder<Question>()
                .name("hibernateReader")
                .sessionFactory(sessionFactory)
                .queryString("from Question")
                .build();
    }

    @Bean
    public ItemProcessor<Question,Question> processor(){
        return q -> q;
    }

    @Bean
    public MongoItemWriter<Question> itemWriter(MongoTemplate template){
        return new MongoItemWriterBuilder<Question>()
                .template(template)
                .collection("Questions")
                .build();
    }

    @Bean
    public Job importQuestionJob(Step migration){
        return jobBuilderFactory
                .get("importQuestionJob")
                .incrementer(new RunIdIncrementer())
                .flow(migration)
                .end()
                .build();
    }


    @Bean
    public Step migration(HibernateCursorItemReader<Question> itemReader,
                          ItemProcessor<Question,Question> processor,
                          MongoItemWriter<Question> writer){

        return stepBuilderFactory
                .get("migration")
                .<Question,Question>chunk(10)
                .reader(itemReader)
                .processor(processor)
                .writer(writer)
                .build();
    }




}
