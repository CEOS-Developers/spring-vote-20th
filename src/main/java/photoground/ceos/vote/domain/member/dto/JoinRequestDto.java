package photoground.ceos.vote.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import photoground.ceos.vote.domain.member.entity.Member;
import photoground.ceos.vote.domain.member.entity.Part;
import photoground.ceos.vote.domain.member.entity.Team;
import photoground.ceos.vote.domain.member.entity.UserRole;

@Getter
@NoArgsConstructor
public class JoinRequestDto {

    private String username;
    private String password;
    private String email;
    private String name;
    private UserRole role;
    private Part part;
    private Team team;

    @Builder
    public JoinRequestDto(String username, String password, String email, String name, UserRole role, Part part, Team team) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.part = part;
        this.team = team;
    }

    public Member toEntity(String encryptedPassword, UserRole role) {
        return Member.builder()
                     .username(this.username)
                     .password(encryptedPassword)
                     .email(this.email)
                     .name(this.name)
                     .role(role)
                     .part(this.part)
                     .team(this.team)
                     .build();
    }
}

