package fr.uha.ensisa.stegmiller.appintav.api.service.modelservices;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.OrganisationDto;
import fr.uha.ensisa.stegmiller.appintav.core.DTOServiceOfModel;
import fr.uha.ensisa.stegmiller.appintav.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public class OrganisationDtoService implements DTOServiceOfModel<OrganisationDto, Organisation> {

    @Override
    public OrganisationDto newInstanceOfDTO() {
        return new OrganisationDto();
    }

    @Override
    public JpaRepository<Organisation, Long> getRepository() {
        return null;
    }
}
