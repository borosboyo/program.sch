package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.SchUserDto;
import hu.bme.aut.programsch.mapper.SchUserMapper;
import hu.bme.aut.programsch.model.SchUser;
import hu.bme.aut.programsch.repository.SchUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchUserService {
    private final SchUserRepository schUserRepository;

    private final SchUserMapper schUserMapper;

    @Transactional
    public SchUserDto findById(long id) {
        Optional<SchUser> schUser = schUserRepository.findById(id);
        return schUser.map(schUserMapper::schUserToDto).orElse(null);
    }

    @Transactional
    public List<SchUserDto> findAll() {
        return schUserMapper.schUsersToDto(schUserRepository.findAll());
    }

    @Transactional
    public SchUserDto createSchUser(SchUserDto schUserDto) {
        return new SchUserDto();
    }

    @Transactional
    public void deleteAll() {
        List<SchUser> schUsers = schUserRepository.findAll();
        for (SchUser b : schUsers) {
            deleteSchUser(b.getId());
        }
    }

    @Transactional
    public void deleteSchUser(long id) {
    }
}
