import io.github.athingx.athing.thing.io.raspberry.pi.ThingIoBoot;

module io.github.athingx.athing.thing.io.raspberry.pi {

    requires io.github.athingx.athing.standard.thing;
    requires io.github.athingx.athing.thing.io;
    requires org.slf4j;
    requires com.pi4j;
    requires static metainf.services;

    provides io.github.athingx.athing.standard.thing.boot.ThingBoot with ThingIoBoot;

}