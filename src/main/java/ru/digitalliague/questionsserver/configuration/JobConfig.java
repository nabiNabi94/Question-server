package ru.digitalliague.questionsserver.configuration;

import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import ru.digitalliague.questionsserver.entity.Answer;
import ru.digitalliague.questionsserver.entity.Question;
import ru.digitalliague.questionsserver.entity.QuestionRowMapper;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class JobConfig {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final DataSource dataSource;

    private final QuestionRowMapper questionRowMapper;


    @Bean
    public JdbcCursorItemReader<Question> jdbcCursorItemReader(){
        return new JdbcCursorItemReaderBuilder<Question>()
                .name("JdbcReader")
                .dataSource(dataSource)
                .beanRowMapper(Question.class)
                .sql("select question0_.que_id as id, level1_.lvl_id as id, profile2_.prof_id as id, answers3_.ans_id as id, question0_.que_lvl_id as id, question0_.que_name as name, question0_.num_of_corr, question0_.que_prof_id as id, level1_.lvl_name as name, profile2_.prof_name as name, answers3_.ans_check as check, answers3_.ans_name as name, answers3_.ans_que_id as ans_que_4_0_3_, answers3_.ans_que_id as ans_que_4_0_0__, answers3_.ans_id as ans_id1_0_0__ from questions question0_ left outer join levels level1_ on question0_.que_lvl_id=level1_.lvl_id left outer join profiles profile2_ on question0_.que_prof_id=profile2_.prof_id left outer join answers answers3_ on question0_.que_id=answers3_.ans_que_id")
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
    public Step migration(JdbcCursorItemReader<Question> itemReader,
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
