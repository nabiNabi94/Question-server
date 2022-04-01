package ru.digitalliague.questionsserver.modelDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.digitalliague.questionsserver.entity.enums.ERoles;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto{

    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private LocalDateTime createdDate;


    public UserDto(Long id,
                   String name,
                   String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }




}
