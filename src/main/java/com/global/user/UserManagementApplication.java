package com.global.user;



//test
import com.global.user.dto.UserDto;
import com.global.user.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementApplication {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Skip mapping for the 'id' field
        modelMapper.typeMap(UserDto.class, User.class).addMappings(mapper ->
                mapper.skip(User::setId)
        );

        return modelMapper;
    }
//    @OpenAPIDefinition(info = @Inf)
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

}
