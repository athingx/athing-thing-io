module io.github.athingx.athing.thing.io.raspberry.pi {

    requires io.github.athingx.athing.standard.thing;
    requires io.github.athingx.athing.thing.io;
    requires org.slf4j;
    requires com.fazecast.jSerialComm;

    exports io.github.athingx.athing.thing.io.raspberry.pi;

    provides io.github.athingx.athing.standard.thing.boot.ThingBoot
            with io.github.athingx.athing.thing.io.raspberry.pi.ThingIoBoot;

}