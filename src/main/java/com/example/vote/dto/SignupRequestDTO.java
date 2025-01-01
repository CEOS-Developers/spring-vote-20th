package com.example.vote.dto;

import com.example.vote.domain.Member.Part;
import com.example.vote.domain.Member.Team;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private Part part;
    private Team team;
}
