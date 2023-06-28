package com.reading.lib.domain.user.mapper;

import com.reading.lib.domain.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto findByEmail(String email);
    void saveUser(String email);
}
