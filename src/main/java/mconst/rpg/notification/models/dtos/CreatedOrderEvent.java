package mconst.rpg.notification.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedOrderEvent implements Serializable {
    private String userId;
    private Long cost;
    private Long count;
}
