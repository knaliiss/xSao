package dev.knalis.xsao.model;

import dev.knalis.xsao.utils.impl.ActionStorage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Script {
    private final String name;
    private final ActionStorage storage;

}

