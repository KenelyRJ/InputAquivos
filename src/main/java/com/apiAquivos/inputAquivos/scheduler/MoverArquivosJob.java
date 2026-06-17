package com.apiAquivos.inputAquivos.scheduler;

import com.apiAquivos.inputAquivos.service.ArquivosService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoverArquivosJob implements Job {

        private final ArquivosService arquivoService;

        @Override
        public void execute(JobExecutionContext context)
                throws JobExecutionException {

            try {
                arquivoService.scheduleRemoveArq();
            } catch (Exception e) {
                throw new JobExecutionException(e);
            }
        }
    }

