package io.github.athingx.athing.thing.io.raspberry.pi;

import io.github.athingx.athing.standard.thing.boot.ThingBoot;
import org.junit.Assert;
import org.junit.Test;

public class ThingIoTestCase {

    @Test
    public void test$thing$io$boot() {
        final ThingBoot boot = new ThingIoBoot();
        Assert.assertEquals("${project.groupId}", boot.getProperties().getProperty(ThingBoot.PROP_GROUP));
        Assert.assertEquals("${project.artifactId}", boot.getProperties().getProperty(ThingBoot.PROP_ARTIFACT));
        Assert.assertEquals("${project.version}", boot.getProperties().getProperty(ThingBoot.PROP_VERSION));
    }

}
