package reddit.restapi.util;

import reddit.restapi.models.User;
import reddit.restapi.models.dtos.SignUpUserDTO;
import org.springframework.stereotype.Component;

@Component
public class SignUpDTOTransformer {

    public User toEntity(SignUpUserDTO dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setUsername(dto.getUsername());
        entity.setDob(dto.getDob());
        entity.setProfileimage(dto.getProfileimage());

        return entity;
    }

    public SignUpUserDTO toDTO(User entity) {
        SignUpUserDTO dto = new SignUpUserDTO();
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        dto.setDob(entity.getDob());
        dto.setProfileimage(entity.getProfileimage());

        return dto;
    }
}
