package io.github.athingx.athing.thing.io.raspberry.pi;

import io.github.athingx.athing.standard.component.ThingCom;
import io.github.athingx.athing.standard.thing.boot.ThingBoot;
import io.github.athingx.athing.standard.thing.boot.ThingBootArgument;
import org.kohsuke.MetaInfServices;

@MetaInfServices
public class ThingIoBoot implements ThingBoot {

    @Override
    public ThingCom[] boot(String productId, String thingId, ThingBootArgument argument) {
        return new ThingCom[]{
                new ThingIoComImpl()
        };
    }

}
