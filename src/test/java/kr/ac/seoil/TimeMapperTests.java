package kr.ac.seoil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ac.seoil.mapper.TimeMapper;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void testGetTime() {
        log.info(timeMapper.getClass().getName());
        log.info("#################" + timeMapper.selectTime());
        log.info("selectTime2:" + timeMapper.selectTime2());
    }
}
