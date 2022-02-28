package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.ClubDto;
import hu.bme.aut.programsch.mapper.ClubMapper;
import hu.bme.aut.programsch.model.Club;
import hu.bme.aut.programsch.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubRepository clubRepository;

    private final ClubMapper clubMapper;

    @Transactional
    public ClubDto findById(long id) {
        Optional<Club> club = clubRepository.findById(id);
        return club.map(clubMapper::clubToDto).orElse(null);
    }

    @Transactional
    public List<ClubDto> findAll() {
        return clubMapper.clubsToDto(clubRepository.findAll());
    }

    @Transactional
    public ClubDto createClub(ClubDto clubDto) {
        return new ClubDto();
    }

    @Transactional
    public void deleteAll() {
        List<Club> clubs = clubRepository.findAll();
        for (Club b : clubs) {
            deleteClub(b.getId());
        }
    }

    @Transactional
    public void deleteClub(long id) {
    }
}
