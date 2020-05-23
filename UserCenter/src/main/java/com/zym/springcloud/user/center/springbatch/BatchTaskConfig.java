//package com.zym.springcloud.user.center.springbatch;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.annotation.Resource;
//import java.util.Date;
//import java.util.List;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchTaskConfig {
//
//    protected Logger log = LoggerFactory.getLogger(getClass());
//    /**
//     * 用于构建JOB
//     */
//    @Resource
//    private JobBuilderFactory jobBuilderFactory;
//
//    /**
//     * 用于构建Step
//     */
//    @Resource
//    private StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * 一个简单基础的Job通常由一个或者多个Step组成
//     */
//    @Bean
//    public Job dataHandleJob() {
//        return jobBuilderFactory.get("Job")
//                .incrementer(new RunIdIncrementer())
//                //start是JOB执行的第一个step
//                .start(handleDataStep())
//                .build();
//    }
//
//
//    /**
//     * 一个简单基础的Step主要分为三个部分
//     * ItemReader : 用于读取数据
//     * ItemProcessor : 用于处理数据
//     * ItemWriter : 用于写数据
//     */
//    @Bean
//    public Step handleDataStep() {
//        return stepBuilderFactory.get("getData")
//                // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
//                .<InData, OutData>chunk(100)
//                //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
//                .faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class)
//                //指定ItemReader
//                .reader(getDataReader())
//                //指定ItemProcessor
//                .processor(getDataProcessor())
//                //指定ItemWriter
//                .writer(getDataWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemReader<? extends InData> getDataReader() {
//        FlatFileItemReader<InData> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("data.csv"));
//        reader.setLineMapper(new LineMapper<InData>() {
//            @Override
//            public InData mapLine(String line, int lineNumber) throws Exception {
//                String[] vList = line.split("\\t");
//                return new InData(Long.valueOf(vList[0]), vList[1], vList[2], vList[3]);
//            }
//        });
//        return reader;
//    }
//
//    @Bean
//    public ItemProcessor<InData, OutData> getDataProcessor() {
//        return new ItemProcessor<InData, OutData>() {
//            @Override
//            public OutData process(InData inData) throws Exception {
//                log.info("processor data : " + inData.toString());
//                return new OutData(inData);
//            }
//        };
//    }
//
//    @Bean
//    public ItemWriter<OutData> getDataWriter() {
//        return new ItemWriter<OutData>() {
//            @Override
//            public void write(List<? extends OutData> list) throws Exception {
//                for (OutData access : list) {
//                    log.info("write data : " + access);
//                }
//            }
//        };
//    }
//
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    @Autowired
//    private Job job;
//
//
////    @Scheduled(fixedDelay = 30000)
//    public void run() {
//        try {
//            String dateParam = new Date().toString();
//            JobParameters param = new JobParametersBuilder().addString("date", dateParam).toJobParameters();
//
//            JobExecution execution = jobLauncher.run(job, param);
//            log.info("任务完成" + execution);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
