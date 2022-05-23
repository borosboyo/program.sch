package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.domain.Membership;
import hu.bme.aut.programsch.domain.Resort;
import hu.bme.aut.programsch.dto.MembershipDto;
import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.mapper.ResortMapper;
import hu.bme.aut.programsch.repository.AppUserRepository;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResortService {
    private final ResortRepository resortRepository;

    private final CircleRepository circleRepository;

    private final AppUserService appUserService;

    private final MembershipService membershipService;

    private final ResortMapper resortMapper;

    @Transactional
    public List<ResortDto> findAll() {
        return resortMapper.resortsToDtos(resortRepository.findAll());
    }

    @Transactional
    public ResortDto findByName(String name) {
        Resort resort = resortRepository.findByName(name);
        List<MembershipDto> memberships = membershipService.getMembershipsByAppUserUid(appUserService.findUser().getUid());
        List<Circle> circles = new ArrayList<>();
        for(MembershipDto membership : memberships) {
            for(Circle circle : resort.getCircles()){
                if(circle.getDisplayName().equals(membership.getCircleName())) {
                    circles.add(circle);
                }
            }
        }
        resort.setCircles(circles);
        return resortMapper.resortToDto(resort);
    }

    @Transactional
    public List<ResortDto> findByMemberships() {
        List<MembershipDto> membershipDtos = membershipService.getMembershipsByAppUserUid(appUserService.findUser().getUid());
        List<Resort> resorts = resortRepository.findAll();
        List<Resort> userResorts = new ArrayList<>();

        for (MembershipDto membershipDto : membershipDtos) {
            for (Resort resort : resorts) {
                if (resort.getCircles().contains(circleRepository.findByDisplayName(membershipDto.getCircleName()))) {
                    userResorts.add(resort);
                }
            }
        }

        return resortMapper.resortsToDtos(userResorts);
    }
}
