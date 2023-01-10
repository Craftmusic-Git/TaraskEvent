package fr.uha.ensisa.stegmiller.appintav.api.service.modelservices;

import fr.uha.ensisa.stegmiller.appintav.api.dto.model.FavorDto;
import fr.uha.ensisa.stegmiller.appintav.core.DTOServiceOfModel;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import fr.uha.ensisa.stegmiller.appintav.persistence.repositories.FavorDAORepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FavorDtoService implements DTOServiceOfModel<FavorDto, Favor> {

    private final FavorDAORepository daoRepository;

    public FavorDtoService(FavorDAORepository daoRepository) {
        this.daoRepository = daoRepository;
    }

    @Override
    public FavorDto newInstanceOfDTO() {
        return new FavorDto();
    }

    @Override
    public JpaRepository<Favor, Long> getRepository() {
        return daoRepository;
    }
}
