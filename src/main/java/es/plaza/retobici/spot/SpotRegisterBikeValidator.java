package es.plaza.retobici.spot;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.function.Function;

import static es.plaza.retobici.spot.SpotRegisterBikeValidator.*;

/**
 * Application of Combinator Pattern for the validation
 */
public interface SpotRegisterBikeValidator extends Function<Spot, ValidationResult> {

    static SpotRegisterBikeValidator isEmpty (){
        return spot -> spot.isEmpty() ? ValidationResult.SUCCESS : ValidationResult.OCCUPIED;
    }

    default SpotRegisterBikeValidator and (SpotRegisterBikeValidator other){
        return spot -> {
            ValidationResult result = this.apply(spot);
            return result.equals(ValidationResult.SUCCESS) ? other.apply(spot) : result;
        };
    }

    enum ValidationResult {
        SUCCESS,
        OCCUPIED,
        RESERVED
    }
}
