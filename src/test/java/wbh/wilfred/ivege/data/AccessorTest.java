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
import java.util.ArrayList;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

@ContextConfiguration(classes = {DataConfig.class})
public abstract class AccessorTest {
    private TestContextManager testContextManager;
    @Autowired
    protected DataSource dataSource;
    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();
    protected static final String ORDER = "`order`";
    protected static final String ORDER_ITEM = "order_item";
    protected static final String DISCOUNT = "discount";
    protected static final String GIFT = "gift";
    protected static final String PRODUCT = "product";
    protected static final String CATEGORY = "category";
    protected static final String DISCOUNT_CATEGORY = "discount_category";
    protected static final String DISCOUNT_PRODUCT = "discount_product";
    protected static final String GIFT_CATEGORY = "gift_category";
    protected static final String GIFT_PRODUCT = "gift_product";

    public void prepare(Operation operationAfterDeleteAll) {
        List<Operation> sequence = new ArrayList<Operation>();
        sequence.add(sequenceOf(deleteAllFrom(
                ORDER_ITEM,
                ORDER,
                GIFT_CATEGORY,
                GIFT_PRODUCT,
                GIFT,
                DISCOUNT_CATEGORY,
                DISCOUNT_PRODUCT,
                DISCOUNT,
                PRODUCT, CATEGORY)));
        sequence.add(operationAfterDeleteAll);
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource),
                sequenceOf(sequence));
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
