package fr.uha.ensisa.stegmiller.appintav.api.dto.model;

import fr.uha.ensisa.stegmiller.appintav.core.DTO;
import fr.uha.ensisa.stegmiller.appintav.core.DTOofModel;
import fr.uha.ensisa.stegmiller.appintav.model.Favor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FavorDto extends DTO implements DTOofModel<Favor> {

    private String title;
    private String description;
    private Integer progress;

    public FavorDto() { }

    public FavorDto(Favor model) {
        dtoOfModel(model);
    }

    @Override
    public Favor modelOfDTO() {
        Favor model = new Favor();
        model.setId(id);
        model.setTitle(title);
        model.setDescription(description);
        model.setProgress(progress);
        return model;
    }

    @Override
    public void dtoOfModel(Favor model) {
        id = model.getId();
        title = model.getTitle();
        description = model.getDescription();
        progress = model.getProgress();
    }
}
