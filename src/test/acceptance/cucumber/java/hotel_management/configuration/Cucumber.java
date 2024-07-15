package hotel_management.configuration;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite(failIfNoTests = false)
@IncludeEngines("cucumber")
@SelectPackages("features")
public class Cucumber {
}
