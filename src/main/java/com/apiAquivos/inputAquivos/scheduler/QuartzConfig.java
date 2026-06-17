package com.apiAquivos.inputAquivos.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {


        @Bean
        public JobDetail moverArquivosJobDetail() {
            return JobBuilder.newJob(MoverArquivosJob.class)
                    .withIdentity("moverArquivosJob")
                    .storeDurably()
                    .build();
        }

        @Bean
        public Trigger moverArquivosTrigger(JobDetail moverArquivosJobDetail) {
            return TriggerBuilder.newTrigger()
                    .forJob(moverArquivosJobDetail)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(30)
                                    .repeatForever()
                    )
                    .build();
        }
    }

