package wbh.wilfred.ivege.data;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import wbh.wilfred.ivege.config.DataConfig;

import javax.sql.DataSource;

@ContextConfiguration(classes = {DataConfig.class})
public abstract class AccessorTest {
    private TestContextManager testContextManager;
    @Autowired
    protected DataSource dataSource;
    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    public void prepare(Operation operation) {
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource),
                operation);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    public void skipNextDbSetup() {
        dbSetupTracker.skipNextLaunch();
    }

    @Before
    public void setUpContext() throws Exception {
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }
}
