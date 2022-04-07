package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.Filter;
import hu.bme.aut.programsch.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;

    @Transactional
    public List<Filter> findAll() {
        return filterRepository.findAll();
    }

    @Transactional
    public Filter findUserFilters(String uid){
        List<Filter> filters = filterRepository.findAll();

        for(Filter filter : filters) {
            if(Objects.equals(filter.getUserId(), uid)){
                return filter;
            }
        }
        return null;
    }

    @Transactional
    public void save(Filter newFilter) {
        filterRepository.save(newFilter);
    }
}
