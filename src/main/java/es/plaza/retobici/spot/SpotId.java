package es.plaza.retobici.spot;

import java.io.Serializable;
import java.util.Objects;

public class SpotId implements Serializable {
    private Long id;
    private Long stop;

    public SpotId() {
    }

    public SpotId(Long id, Long stop) {
        this.id = id;
        this.stop = stop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStop() {
        return stop;
    }

    public void setStop(Long stop) {
        this.stop = stop;
    }
}
