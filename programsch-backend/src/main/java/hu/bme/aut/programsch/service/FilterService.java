package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.domain.Filter;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.mapper.FilterMapper;
import hu.bme.aut.programsch.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;
    private final FilterMapper filterMapper;

    @Transactional
    public List<Filter> findAll() {
        return filterRepository.findAll();
    }

    @Transactional
    public FilterDto findUserFilters(String uid) {
        List<Filter> filters = filterRepository.findAll();

        for (Filter filter : filters) {
            if (Objects.equals(filter.getUserId(), uid)) {
                return filterMapper.filterToDto(filter);
            }
        }
        return null;
    }

    @Transactional
    public void save(Filter newFilter) {
        filterRepository.save(newFilter);
    }

    @Transactional
    public void delete(FilterDto filter) {
        filterRepository.findById(filter.getId()).ifPresent(filterRepository::delete);
    }

    @Transactional
    public FilterDto createNewFilter(String uid) {
        Filter filter = new Filter();
        filter.setUserId(uid);
        return filterMapper.filterToDto(filterRepository.save(filter));
    }

    @Transactional
    public FilterDto changeUserFilters(FilterDto filterDto) {
        Filter filter = filterRepository.findById(filterDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Filter not found"));
        filter.setFilteredCircles(filterDto.getFilteredCircles());
        return filterMapper.filterToDto(filterRepository.save(filter));
    }
}
