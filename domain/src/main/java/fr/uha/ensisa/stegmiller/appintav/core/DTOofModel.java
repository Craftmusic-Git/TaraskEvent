package fr.uha.ensisa.stegmiller.appintav.core;

public interface DTOofModel<M extends Model> {
    M modelOfDTO();
    void dtoOfModel(M model);
}
