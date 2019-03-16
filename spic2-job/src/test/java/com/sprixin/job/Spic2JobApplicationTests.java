package com.sprixin.job;

import com.sprixin.job.util.CustomJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Spic2JobApplicationTests {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private Scheduler scheduler;

    @Test
    public void contextLoads() throws SchedulerException {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail job = JobBuilder.newJob(CustomJob.class)
                .withIdentity("myJob2")
                .storeDurably()
                .build();
//        scheduler.addJob(job,true);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "myTriggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .startAt(DateBuilder.futureDate(10, DateBuilder.IntervalUnit.MINUTE))
                .build();

//        scheduler.scheduleJob(job, trigger);

    }

}
