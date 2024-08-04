package com.example.purithm.global.aws.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.purithm.global.auth.annotation.LoginInfo;
import com.example.purithm.global.aws.dto.PresignedUrlDto;
import com.example.purithm.global.aws.service.FileService;
import com.example.purithm.global.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
	private final FileService fileService;

	@PostMapping
	public SuccessResponse<PresignedUrlDto> getPresignedUrl(@LoginInfo Long id,
		@RequestParam @Parameter(description = "이미지 저장 시 prefix",
			examples = {@ExampleObject(name = "review 관련 이미지 url", value = "review"), @ExampleObject(name = "user 프로필 관련 이미지 url", value = "user")}) String prefix) {
		return SuccessResponse.of(fileService.createPresignedUrl(prefix, id));
	}

}
