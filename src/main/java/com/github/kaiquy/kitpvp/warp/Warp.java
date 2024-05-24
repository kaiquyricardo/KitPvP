package com.github.kaiquy.kitpvp.warp;

import com.github.kaiquy.kitpvp.misc.SimpleLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Warp  {

    private WarpsType type;
    private SimpleLocation location;

}
