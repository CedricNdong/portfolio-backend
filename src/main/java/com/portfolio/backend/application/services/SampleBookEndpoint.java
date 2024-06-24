package com.portfolio.backend.application.services;

import com.portfolio.backend.application.data.SampleBook;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.exception.EndpointException;
import jakarta.annotation.security.RolesAllowed;
import java.util.Optional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Endpoint
@RolesAllowed("ADMIN")
public class SampleBookEndpoint {

    private final SampleBookService service;

    public SampleBookEndpoint(SampleBookService service) {
        this.service = service;
    }

    public Page<SampleBook> list(Pageable page) {
        return service.list(page);
    }

    public Optional<SampleBook> get(Long id) {
        return service.get(id);
    }

    public SampleBook update(SampleBook entity) {
        try {
            return service.update(entity);
        } catch (OptimisticLockingFailureException e) {
            throw new EndpointException("Somebody else has updated the data while you were making changes.");
        }
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public int count() {
        return service.count();
    }

}