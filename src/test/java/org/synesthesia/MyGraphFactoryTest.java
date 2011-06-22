package org.synesthesia;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Ignore;
import org.junit.Test;
import org.synesthesia.MyGraphFactory;

public class MyGraphFactoryTest {
	
	String[] inputArgs = new String[] {
			"workspace/scp-test/src/main/java/com/syncron/scp/test/dbcompare/calc/copytoarchive/OneOrderlineOutsideDataOwner2Test.java:import com.syncron.scp.test.dbcompare.calc.BaseCalcTestCase;",
			"workspace/scp-app/src/jee-module/java/com/syncron/scp/activity/ActivityMonitor.java:import java.util.HashMap;",
			"workspace/scp-test/src/main/java/com/syncron/scp/test/dbcompare/calc/copytoarchive/OneOrderlineInsideItemTest.java:import com.syncron.scp.test.dbcompare.calc.BaseCalcTestCase;",
			"workspace/SCPCore/src/main/java/com/syncron/model/forecasting/SupersessionChain.java:import java.util.Date;",
			"workspace/SCPGUI/src/main/java/com/syncron/scp/web/managedbeans/views/SCPWorksheetVirtualHierarchyBean.java:import com.syncron.bpe.core.transfer.genericquery.Query;",
			"workspace/SCPRegressionTest/src/com/syncron/test/daounit/BaseDAOTest.java:import oracle.toplink.expressions.Expression;",
			"workspace/SCPRun/src/main/java/com/syncron/run/jobs/CalcForecast.java:import com.syncron.model.forecasting.TotalForecasts;",
			"workspace/SyncAppServer/src/se/sync/bpiw/x/servlet/SyncCheckout.java:import javax.servlet.http.HttpServlet;",
			"workspace/SyncronATF/src/main/java/com/syncron/selenium/helper/core/SyncronSelenium.java:import java.text.SimpleDateFormat;",
			"workspace/SyncronBPPTest/src/main/java/com/syncron/selenium/tests/viewstests/scheduler/SchedulerScreenTestTemplate.java:import com.syncron.selenium.tests.viewstests.scheduler.scheduledtasks.GuiScheduledTaskAssert;",
			"workspace/SyncronBusinessFoundation/src/se/sync/bop/item/IItemWarehouse.java:import se.sync.bop.IBop;",
			"workspace/SyncronEngine/src/main/java/com/syncron/bpe/engine/batch/tr/TrBatchWeb.java:import com.syncron.bpe.engine.sysdoc.bo.InterfaceInstance;",
			"workspace/SyncronFaces/src/main/java/com/syncron/faces/components/duallist/renderers/HtmlSyncDualListRenderer.java:import java.util.LinkedHashSet;",
			"workspace/SyncronUtil/src/se/sync/util/excel/ExcelXmlWorkSheet.java:import com.google.common.base.Strings;",
			"workspace/SyncronWebService/src/com/syncron/bpe/transfer/dtoentity/item/ItemDTO.java:import com.syncron.bpe.core.transfer.dtoentity.DTOEntityWithVersionHistory;",
			"workspace/SyncronBusinessFoundation/src/se/sync/tre/mp/TrMpAdr03.java: import java.util.Properties;",};
	Object[][] expected = new Object[][] {
			new String[]{"scp-test", "src/main/java", "com.syncron.scp.test.dbcompare.calc.copytoarchive.OneOrderlineOutsideDataOwner2Test", "com.syncron.scp.test.dbcompare.calc.BaseCalcTestCase"},
			new String[]{"scp-app", "src/jee-module/java","com.syncron.scp.activity.ActivityMonitor",                                         "java.util.HashMap"},
			new String[]{"scp-test", "src/main/java",     "com.syncron.scp.test.dbcompare.calc.copytoarchive.OneOrderlineInsideItemTest",     "com.syncron.scp.test.dbcompare.calc.BaseCalcTestCase"},
			new String[]{"SCPCore", "src/main/java",      "com.syncron.model.forecasting.SupersessionChain",                                  "java.util.Date"},
			new String[]{"SCPGUI", "src/main/java",       "com.syncron.scp.web.managedbeans.views.SCPWorksheetVirtualHierarchyBean",          "com.syncron.bpe.core.transfer.genericquery.Query"},
			new String[]{"SCPRegressionTest","src",       "com.syncron.test.daounit.BaseDAOTest",                                             "oracle.toplink.expressions.Expression"},
			new String[]{"SCPRun","src/main/java",        "com.syncron.run.jobs.CalcForecast",                                                "com.syncron.model.forecasting.TotalForecasts"},
			new String[]{"SyncAppServer","src",           "se.sync.bpiw.x.servlet.SyncCheckout",                                              "javax.servlet.http.HttpServlet"},
			new String[]{"SyncronATF","src/main/java",    "com.syncron.selenium.helper.core.SyncronSelenium",                                 "java.text.SimpleDateFormat"},
			new String[]{"SyncronBPPTest","src/main/java","com.syncron.selenium.tests.viewstests.scheduler.SchedulerScreenTestTemplate",      "com.syncron.selenium.tests.viewstests.scheduler.scheduledtasks.GuiScheduledTaskAssert"},
			new String[]{"SyncronBusinessFoundation","src","se.sync.bop.item.IItemWarehouse",                                                  "se.sync.bop.IBop"},
			new String[]{"SyncronEngine","src/main/java", "com.syncron.bpe.engine.batch.tr.TrBatchWeb",                                       "com.syncron.bpe.engine.sysdoc.bo.InterfaceInstance"},
			new String[]{"SyncronFaces","src/main/java",  "com.syncron.faces.components.duallist.renderers.HtmlSyncDualListRenderer",         "java.util.LinkedHashSet"},
			new String[]{"SyncronUtil","src",             "se.sync.util.excel.ExcelXmlWorkSheet",                                             "com.google.common.base.Strings"},
			new String[]{"SyncronWebService","src",       "com.syncron.bpe.transfer.dtoentity.item.ItemDTO",                                  "com.syncron.bpe.core.transfer.dtoentity.DTOEntityWithVersionHistory"},
			new String[]{"SyncronBusinessFoundation","src","se.sync.tre.mp.TrMpAdr03",                                                        "java.util.Properties"},
	};
	@Ignore
	@Test
	public void test() throws Throwable{
		LinkedList<Throwable> errors = new LinkedList<Throwable>();
		for(int i = 0; i < inputArgs.length; i++){
			try{
				
				assertEquals(expected[i], MyGraphFactory.importToStrings(inputArgs[i]));
			}catch(Throwable e){
				System.err.println(i);
				for(int j = 0; j < 4; j++){
					System.err.println((expected[i])[j]);
				}
				
				errors.add(e);
			}
		}
		if(errors.size() > 0){
			for(Throwable e : errors){
				e.printStackTrace();	
			}
			
			throw errors.get(0);
		}
	}
}