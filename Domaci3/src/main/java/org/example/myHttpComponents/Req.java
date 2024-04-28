package org.example.myHttpComponents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

@Data
@Getter
@AllArgsConstructor
public class Req {
    private Type type;
    private String route;
    private HashMap<String ,String> body;

}
