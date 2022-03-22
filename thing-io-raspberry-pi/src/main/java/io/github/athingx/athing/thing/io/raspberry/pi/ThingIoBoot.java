package io.github.athingx.athing.thing.io.raspberry.pi;

import io.github.athingx.athing.standard.component.ThingCom;
import io.github.athingx.athing.standard.thing.boot.ThingBoot;
import io.github.athingx.athing.standard.thing.boot.ThingBootArgument;

import java.util.Properties;

public class ThingIoBoot implements ThingBoot {

    @Override
    public ThingCom[] boot(String productId, String thingId, ThingBootArgument argument) {
        return new ThingCom[]{
                new ThingIoComImpl()
        };
    }

    @Override
    public Properties getProperties() {
        return new Properties(){{
            put(PROP_GROUP, "io.github.athingx.athing.thing.io");
            put(PROP_ARTIFACT, "thing-io-raspberry-pi");
        }};
    }

}
