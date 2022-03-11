package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.AppUserEntity;
import hu.bme.aut.programsch.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Transactional
    public AppUserEntity getById(String uid) {
        return appUserRepository.getById(uid);
    }

    @Transactional
    public boolean exists(String uid) {
        return appUserRepository.existsById(uid);
    }

    @Transactional
    public void save(AppUserEntity appUserEntity) {
        appUserRepository.save(appUserEntity);
    }

    @Transactional
    public List<AppUserEntity> findAll() {
        return appUserRepository.findAll();
    }

}
