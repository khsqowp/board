package kr.ac.seoil.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {

    @Select("SELECT SYSDATE FROM DUAL")
    public String selectTime();

//    @Select("SELECT CURRENT_TIMESTAMP")
    public String selectTime2();
}
