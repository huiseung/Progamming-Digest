package com.example.batch;


import com.example.batch.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InactiveJobConfig {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void 휴먼회원_전환_테스트() throws Exception{
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();




    }
}
