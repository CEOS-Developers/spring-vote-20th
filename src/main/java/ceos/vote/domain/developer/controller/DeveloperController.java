package ceos.vote.domain.developer.controller;
import ceos.vote.domain.developer.dto.DeveloperRequestDto;
import ceos.vote.domain.developer.service.DeveloperService;
import ceos.vote.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/developer")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public CommonResponse<Void> createDeveloper(@RequestBody DeveloperRequestDto developerRequestDto) {

        developerService.createDeveloper(developerRequestDto);
        return new CommonResponse<>("개발자 생성 완료");
    }
}
