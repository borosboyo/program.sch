package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.dto.CircleFilterDto;
import hu.bme.aut.programsch.mapper.AppUserMapper;
import hu.bme.aut.programsch.model.AppUser;
import hu.bme.aut.programsch.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    private final CircleService circleService;

    @Transactional
    public AppUser getById(String uid) {
        return appUserRepository.getById(uid);
    }

    @Transactional
    public AppUserDto getByIdDto(String uid) {
        return appUserMapper.appUserEntityToDto(appUserRepository.getById(uid));
    }

    @Transactional
    public boolean exists(String uid) {
        return appUserRepository.existsById(uid);
    }

    @Transactional
    public AppUserDto save(AppUserDto appUserDto) {
        AppUser appUserEntity = appUserRepository.save(
                new AppUser(
                        appUserDto.getName(),
                        appUserDto.getEmail()));
        return appUserMapper.appUserEntityToDto(appUserEntity);
    }

    @Transactional
    public AppUserDto save(AppUser appUser) {
        return appUserMapper.appUserEntityToDto(appUserRepository.save(appUser));
    }

    @Transactional
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Transactional
    public List<AppUserDto> findAllDto() {
        return appUserMapper.appUserEntitiesToDtos(appUserRepository.findAll());
    }

    @Transactional
    public List<CircleFilterDto> findUserFilters() {
        return appUserMapper.appUserEntityToDto(appUserRepository.findAll().get(0)).getFilters();
    }
}
