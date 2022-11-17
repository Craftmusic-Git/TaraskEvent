package fr.uha.ensisa.stegmiller.appintav.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface DTOServiceOfModel<DTO extends DTOofModel<M>, M extends Model<M>>{
    DTO newInstanceOfDTO();
    JpaRepository<M, Long> getRepository();

    default M create(DTO dto){
        return getRepository().save(dtoToModel(dto));
    }

    default Iterable<M> create(Iterable<DTO> dtos){
        return getRepository().saveAll(dtoToModelList(dtos));
    }

    default Optional<DTO> getById(Long id){
        if(getRepository().existsById(id))
            return Optional.of(modelToDTO(getRepository().findById(id).get()));
        else return Optional.empty();
    }

    default Iterable<DTO> getAllById(Iterable<Long> ids){
        return modelToDTOList(getRepository().findAllById(ids));
    }

    default Iterable<DTO> getAll(){
        return modelToDTOList(getRepository().findAll());
    }

    default M update(DTO dto){
        Optional<M> model = getRepository().findById(dtoToModel(dto).id);
        return model.map(m -> (M) m.update(m)).orElse(null);
    }

    default void removeById(Long id){
        getRepository().deleteById(id);
    }

    default void removeAll(Iterable<Long> ids){
        getRepository().deleteAllById(ids);
    }

    default Iterable<M> dtoToModelList(Iterable<DTO> dtos){
        List<M> rep = new ArrayList<>();
        for(DTO dto : dtos){
            rep.add(dtoToModel(dto));
        }
        return rep;
    }

    default Iterable<DTO> modelToDTOList(Iterable<M> models){
        List<DTO> rep = new ArrayList<>();
        for(M m : models){
            rep.add(modelToDTO(m));
        }
        return rep;
    }

    default DTO modelToDTO(M model){
        DTO dto = newInstanceOfDTO();
        dto.dtoOfModel(model);
        return dto;
    }

    default M dtoToModel(DTO dto){
        return dto.modelOfDTO();
    }
}
