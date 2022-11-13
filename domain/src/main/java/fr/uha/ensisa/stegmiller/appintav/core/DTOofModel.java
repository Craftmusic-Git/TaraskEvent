package fr.uha.ensisa.stegmiller.appintav.core;

public interface DTOofModel<M extends Model> {
    M ModelOfDTO(DTOofModel dto);
    DTOofModel DTOofModel(M model);
}
