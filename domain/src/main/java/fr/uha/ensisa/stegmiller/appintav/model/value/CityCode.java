package fr.uha.ensisa.stegmiller.appintav.model.value;

public class CityCode {
    private String code;

    public CityCode(String value) {
        this.code = Check(value);
    }

    private String Check(String value) {
        var sanitized = value.trim();
        var regex = "^[0-9]{5}";
        if (sanitized.matches(regex))
            return sanitized;
        throw new Error("code postal invalide");
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CityCode other = (CityCode) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }
}
