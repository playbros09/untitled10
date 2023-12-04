package com.ua.transport.mapper;

import com.ua.model.User;
import com.ua.transport.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
@Component
@Mapper(uses = {TicketMapper.class}, componentModel = "spring")
public interface UserMapper {


 // Page<UserDTO> toPageDto(Page<User> entityPage);

    UserDTO toDto(User entity);
    User toEntity(UserDTO dto);

    void update(UserDTO dto,@MappingTarget User user);

}
