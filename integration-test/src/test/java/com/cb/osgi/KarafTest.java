package com.cb.osgi;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel; 
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.editConfigurationFilePut; 

import java.io.File;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ops4j.pax.exam.karaf.options.LogLevelOption.LogLevel; 

import com.cb.opsysinc.simple.service.api.SimpleService;

@RunWith(PaxExam.class)
public class KarafTest {

    private static Logger LOG = LoggerFactory.getLogger(KarafTest.class);

    @Inject
    protected SimpleService simpleService;

    @Configuration
    public Option[] config() {
        MavenArtifactUrlReference karafUrl = maven()
            .groupId("org.apache.karaf")
            .artifactId("apache-karaf")
            .versionAsInProject()
            .type("zip");
        
        String karafVersion = karafUrl.getURL().split("\\/")[2];
        LOG.info("karafVersion [{}]", karafVersion);

        MavenUrlReference karafStandardRepo = maven()
            .groupId("org.apache.karaf.features")
            .artifactId("standard")
            .version(karafVersion)
            .classifier("features")
            .type("xml");

        return new Option[] {
            karafDistributionConfiguration()
                .frameworkUrl(karafUrl)
                .unpackDirectory(new File("target", "exam"))
                .useDeployFolder(false),
            keepRuntimeFolder(),
            logLevel(LogLevel.WARN),
            configureConsole().ignoreLocalConsole(),
            editConfigurationFilePut("etc/org.ops4j.pax.logging.cfg", "log4j.logger.com.cb", "INFO"), 
            features(karafStandardRepo , "scr"),
            mavenBundle()
                .groupId("org.apache.felix")
                .artifactId("org.apache.felix.ipojo")
                .versionAsInProject().start(),
            mavenBundle()
                .groupId("org.apache.felix")
                .artifactId("org.apache.felix.ipojo.api")
                .versionAsInProject().start(),
            mavenBundle()
                .groupId("org.apache.felix")
                .artifactId("org.apache.felix.ipojo.composite")
                .versionAsInProject().start(),
            mavenBundle()
                .groupId("com.cb.osgi")
                .artifactId("simple-api")
                .versionAsInProject().start(),
            mavenBundle()
                .groupId("com.cb.osgi")
                .artifactId("simple-server")
                .versionAsInProject().start(),
            mavenBundle()
                .groupId("com.cb.osgi")
                .artifactId("simple-client")
                .versionAsInProject().start(),
       };
    }

    @Test
    public void testSimpleServerFromContainer() {
    	simpleService.sayHello();
    	String msg = simpleService.getMessage();
        LOG.info("Result of getMessage was {}", msg);
        Assert.assertEquals("Howdy", msg);
    }

}

